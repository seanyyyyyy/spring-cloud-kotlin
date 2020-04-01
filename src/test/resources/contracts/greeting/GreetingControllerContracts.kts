import org.springframework.cloud.contract.spec.ContractDsl.Companion.contract
import org.springframework.cloud.contract.spec.withQueryParameters

contract {
    name = "some name"
    description = "Some description"
    priority = 8
    ignored = true
    request {
        url = url("/foo") withQueryParameters  {
            parameter("a", "b")
            parameter("b", "c")
        }
        method = PUT
        headers {
            header("foo", value(client(regex("bar")), server("bar")))
            header("fooReq", "baz")
        }
        body = body(mapOf("foo" to "bar"))
        bodyMatchers {
            jsonPath("$.foo", byRegex("bar"))
        }
    }
    response {
        delay = fixedMilliseconds(1000)
        status = OK
        headers {
            header("foo2", value(server(regex("bar")), client("bar")))
            header("foo3", value(server(execute("andMeToo(\$it)")), client("foo33")))
            header("fooRes", "baz")
        }
        body = body(mapOf(
                "foo" to "bar",
                "foo3" to "baz",
                "nullValue" to null
        ))
        bodyMatchers {
            jsonPath("$.foo2", byRegex("bar"))
            jsonPath("$.foo3", byCommand("executeMe(\$it)"))
            jsonPath("$.nullValue", byNull)
        }
    }
}