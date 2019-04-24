package specs.styleguide.components.links

import spock.lang.Stepwise
import spock.lang.Unroll
import support.ComponentSpec

@Stepwise
class DLSLinksScreenshotSpec extends ComponentSpec {

    String pathPage = "styleguide/components/link"
    String pathSite = "content/swinburne-showcase"
    String language = "en"
    String componentPath = "jcr:content/article/par/contentblock_20/"

    def setupSpec() {
        loginAsAdmin()
    }

	@Unroll("Appearance of Link with default variant")
    def "Appearance of Link with default variant"() {

        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#link_1"

        when: 'I am in the component showcase page'
        setWindowSize()
        waitForAuthorPreviewPage()

        then: 'The component should appear on the page'
        def component = waitForComponent(selector)

        then: 'It should match reference image.'
        designRef(selector)

    }

	@Unroll("Appearance of Link with variant primary yellow")
	def "Appearance of Link with variant primary yellow"() {

		given: '>I am in the component showcase page'
		and: '>the component is on the showcase page'
		def selector = "#link_2"

		when: 'I am in the component showcase page'
		setWindowSize()
		waitForAuthorPreviewPage()

		then: 'The component should appear on the page'
		def component = waitForComponent(selector)

		then: 'It should match reference image.'
		designRef(selector)

	}

	@Unroll("Appearance of Link with default variant disabled")
	def "Appearance of Link with default variant disabled"() {

		given: '>I am in the component showcase page'
		and: '>the component is on the showcase page'
		def selector = "#link_3"

		when: 'I am in the component showcase page'
		setWindowSize()
		waitForAuthorPreviewPage()

		then: 'The component should appear on the page'
		def component = waitForComponent(selector)

		then: 'It should match reference image.'
		designRef(selector)

	}

	@Unroll("Appearance of Link with default variant secondary outline")
	def "Appearance of Link with default variant secondary outline"() {

		given: '>I am in the component showcase page'
		and: '>the component is on the showcase page'
		def selector = "#link_4"

		when: 'I am in the component showcase page'
		setWindowSize()
		waitForAuthorPreviewPage()

		then: 'The component should appear on the page'
		def component = waitForComponent(selector)

		then: 'It should match reference image.'
		designRef(selector)

	}

	@Unroll("Appearance of Link with default variant secondary outline charcoal")
    def "Appearance of Link with default variant secondary outline charcoal"() {

        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#link_5"

        when: 'I am in the component showcase page'
        setWindowSize()
        waitForAuthorPreviewPage()

        then: 'The component should appear on the page'
        def component = waitForComponent(selector)

        then: 'It should match reference image.'
        designRef(selector)

    }

	@Unroll("Appearance of Link with default variant secondary charcoal")
	def "Appearance of Link with default variant secondary charcoal"() {

		given: '>I am in the component showcase page'
		and: '>the component is on the showcase page'
		def selector = "#link_6"

		when: 'I am in the component showcase page'
		setWindowSize()
		waitForAuthorPreviewPage()

		then: 'The component should appear on the page'
		def component = waitForComponent(selector)

		then: 'It should match reference image.'
		designRef(selector)

	}

	@Unroll("Appearance of Link with default variant disabled outline")
	def "Appearance of Link with default variant disabled outline"() {

		given: '>I am in the component showcase page'
		and: '>the component is on the showcase page'
		def selector = "#link_7"

		when: 'I am in the component showcase page'
		setWindowSize()
		waitForAuthorPreviewPage()

		then: 'The component should appear on the page'
		def component = waitForComponent(selector)

		then: 'It should match reference image.'
		designRef(selector)

	}

	@Unroll("Appearance of Link with default variant primary red")
	def "Appearance of Link with default variant primary red"() {

		given: '>I am in the component showcase page'
		and: '>the component is on the showcase page'
		def selector = "#link_8"

		when: 'I am in the component showcase page'
		setWindowSize()
		waitForAuthorPreviewPage()

		then: 'The component should appear on the page'
		def component = waitForComponent(selector)

		then: 'It should match reference image.'
		designRef(selector)

	}

	@Unroll("Appearance of Link on charcoal background with variant primary yellow")
	def "Appearance of Link on charcoal background with variant primary yellow"() {

		given: '>I am in the component showcase page'
		and: '>the component is on the showcase page'
		def selector = "#link_9"

		when: 'I am in the component showcase page'
		setWindowSize()
		waitForAuthorPreviewPage()

		then: 'The component should appear on the page'
		def component = waitForComponent(selector)

		then: 'It should match reference image.'
		designRef(selector)

	}

	@Unroll("Appearance of Link on charcoal background with default variant disabled")
	def "Appearance of Link on charcoal background with default variant disabled"() {

		given: '>I am in the component showcase page'
		and: '>the component is on the showcase page'
		def selector = "#link_10"

		when: 'I am in the component showcase page'
		setWindowSize()
		waitForAuthorPreviewPage()

		then: 'The component should appear on the page'
		def component = waitForComponent(selector)

		then: 'It should match reference image.'
		designRef(selector)

	}

	@Unroll("Appearance of Link on charcoal background with default variant secondary outline")
	def "Appearance of Link on charcoal background with default variant secondary outline"() {

		given: '>I am in the component showcase page'
		and: '>the component is on the showcase page'
		def selector = "#link_11"

		when: 'I am in the component showcase page'
		setWindowSize()
		waitForAuthorPreviewPage()

		then: 'The component should appear on the page'
		def component = waitForComponent(selector)

		then: 'It should match reference image.'
		designRef(selector)

	}

	@Unroll("Appearance of Link on charcoal background with default variant secondary outline charcoal")
	def "Appearance of Link on charcoal background with default variant secondary outline charcoal"() {

		given: '>I am in the component showcase page'
		and: '>the component is on the showcase page'
		def selector = "#link_12"

		when: 'I am in the component showcase page'
		setWindowSize()
		waitForAuthorPreviewPage()

		then: 'The component should appear on the page'
		def component = waitForComponent(selector)

		then: 'It should match reference image.'
		designRef(selector)

	}

	@Unroll("Appearance of Link on charcoal background with default variant secondary charcoal")
	def "Appearance of Link on charcoal background with default variant secondary charcoal"() {

		given: '>I am in the component showcase page'
		and: '>the component is on the showcase page'
		def selector = "#link_13"

		when: 'I am in the component showcase page'
		setWindowSize()
		waitForAuthorPreviewPage()

		then: 'The component should appear on the page'
		def component = waitForComponent(selector)

		then: 'It should match reference image.'
		designRef(selector)

	}

	@Unroll("Appearance of Link on charcoal background with default variant disabled outline")
	def "Appearance of Link on charcoal background with default variant disabled outline"() {

		given: '>I am in the component showcase page'
		and: '>the component is on the showcase page'
		def selector = "#link_14"

		when: 'I am in the component showcase page'
		setWindowSize()
		waitForAuthorPreviewPage()

		then: 'The component should appear on the page'
		def component = waitForComponent(selector)

		then: 'It should match reference image.'
		designRef(selector)

	}

	@Unroll("Appearance of Link on charcoal background with default variant primary red")
	def "Appearance of Link on charcoal background with default variant primary red"() {

		given: '>I am in the component showcase page'
		and: '>the component is on the showcase page'
		def selector = "#link_15"

		when: 'I am in the component showcase page'
		setWindowSize()
		waitForAuthorPreviewPage()

		then: 'The component should appear on the page'
		def component = waitForComponent(selector)

		then: 'It should match reference image.'
		designRef(selector)

	}

}
