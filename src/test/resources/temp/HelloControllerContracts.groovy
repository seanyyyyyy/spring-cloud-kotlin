package temp

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description('''
        given:
            An input
        when:
            Sth happens
        then:
            Output
        ''')
    name("some_special_name")
    request {
        method GET()
        url "/hello"
        headers {

        }
    }
    response {
        status OK()
        headers {

        }
        body ("Hello, world!")
    }
}