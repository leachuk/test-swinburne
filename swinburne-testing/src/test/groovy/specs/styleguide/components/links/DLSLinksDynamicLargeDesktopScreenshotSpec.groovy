package specs.styleguide.components.links

import spock.lang.Stepwise
import spock.lang.Unroll
import support.ComponentSpec

@Stepwise
class DLSLinksDynamicLargeDesktopScreenshotSpec extends ComponentSpec {

    String pathPage = "styleguide/components/link"
    String pathSite = "content/swinburne-showcase"
    String language = "en"
    String componentPath = "jcr:content/article/par/contentblock_20/"

    def setupSpec() {
        loginAsAdmin()
    }

	@Unroll("Appearance of Link with default variant on Large Desktop")
    def "Appearance of Link with default variant on Large Desktop"() {

        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#link_1"

        when: 'I am in the component showcase page'
        setWindowSizeXLG()
        waitForAuthorPreviewPage()

        then: 'The component should appear on the page'
        def component = waitForComponent(selector)

        then: 'It should match reference image.'
        designRef(selector)

    }

	@Unroll("Appearance of Link with variant primary yellow on Large Desktop")
	def "Appearance of Link with variant primary yellow on Large Desktop"() {

		given: '>I am in the component showcase page'
		and: '>the component is on the showcase page'
		def selector = "#link_2"

		when: 'I am in the component showcase page'
		setWindowSizeXLG()
		waitForAuthorPreviewPage()

		then: 'The component should appear on the page'
		def component = waitForComponent(selector)

		then: 'It should match reference image.'
		designRef(selector)

	}

	@Unroll("Appearance of Link with default variant disabled on Large Desktop")
	def "Appearance of Link with default variant disabled on Large Desktop"() {

		given: '>I am in the component showcase page'
		and: '>the component is on the showcase page'
		def selector = "#link_3"

		when: 'I am in the component showcase page'
		setWindowSizeXLG()
		waitForAuthorPreviewPage()

		then: 'The component should appear on the page'
		def component = waitForComponent(selector)

		then: 'It should match reference image.'
		designRef(selector)

	}

	@Unroll("Appearance of Link with default variant secondary outline on Large Desktop")
	def "Appearance of Link with default variant secondary outline on Large Desktop"() {

		given: '>I am in the component showcase page'
		and: '>the component is on the showcase page'
		def selector = "#link_4"

		when: 'I am in the component showcase page'
		setWindowSizeXLG()
		waitForAuthorPreviewPage()

		then: 'The component should appear on the page'
		def component = waitForComponent(selector)

		then: 'It should match reference image.'
		designRef(selector)

	}

	@Unroll("Appearance of Link with default variant secondary outline charcoal on Large Desktop")
    def "Appearance of Link with default variant secondary outline charcoal on Large Desktop"() {

        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#link_5"

        when: 'I am in the component showcase page'
        setWindowSizeXLG()
        waitForAuthorPreviewPage()

        then: 'The component should appear on the page'
        def component = waitForComponent(selector)

        then: 'It should match reference image.'
        designRef(selector)

    }

	@Unroll("Appearance of Link with default variant secondary charcoal on Large Desktop")
	def "Appearance of Link with default variant secondary charcoal on Large Desktop"() {

		given: '>I am in the component showcase page'
		and: '>the component is on the showcase page'
		def selector = "#link_6"

		when: 'I am in the component showcase page'
		setWindowSizeXLG()
		waitForAuthorPreviewPage()

		then: 'The component should appear on the page'
		def component = waitForComponent(selector)

		then: 'It should match reference image.'
		designRef(selector)

	}

	@Unroll("Appearance of Link with default variant disabled outline on Large Desktop")
	def "Appearance of Link with default variant disabled outline on Large Desktop"() {

		given: '>I am in the component showcase page'
		and: '>the component is on the showcase page'
		def selector = "#link_7"

		when: 'I am in the component showcase page'
		setWindowSizeXLG()
		waitForAuthorPreviewPage()

		then: 'The component should appear on the page'
		def component = waitForComponent(selector)

		then: 'It should match reference image.'
		designRef(selector)

	}

	@Unroll("Appearance of Link with default variant primary red on Large Desktop")
	def "Appearance of Link with default variant primary red on Large Desktop"() {

		given: '>I am in the component showcase page'
		and: '>the component is on the showcase page'
		def selector = "#link_8"

		when: 'I am in the component showcase page'
		setWindowSizeXLG()
		waitForAuthorPreviewPage()

		then: 'The component should appear on the page'
		def component = waitForComponent(selector)

		then: 'It should match reference image.'
		designRef(selector)

	}

	@Unroll("Appearance of Link on charcoal background with variant primary yellow on Large Desktop")
	def "Appearance of Link on charcoal background with variant primary yellow on Large Desktop"() {

		given: '>I am in the component showcase page'
		and: '>the component is on the showcase page'
		def selector = "#link_9"

		when: 'I am in the component showcase page'
		setWindowSizeXLG()
		waitForAuthorPreviewPage()

		then: 'The component should appear on the page'
		def component = waitForComponent(selector)

		then: 'It should match reference image.'
		designRef(selector)

	}

	@Unroll("Appearance of Link on charcoal background with default variant disabled on Large Desktop")
	def "Appearance of Link on charcoal background with default variant disabled on Large Desktop"() {

		given: '>I am in the component showcase page'
		and: '>the component is on the showcase page'
		def selector = "#link_10"

		when: 'I am in the component showcase page'
		setWindowSizeXLG()
		waitForAuthorPreviewPage()

		then: 'The component should appear on the page'
		def component = waitForComponent(selector)

		then: 'It should match reference image.'
		designRef(selector)

	}

	@Unroll("Appearance of Link on charcoal background with default variant secondary outline on Large Desktop")
	def "Appearance of Link on charcoal background with default variant secondary outline on Large Desktop"() {

		given: '>I am in the component showcase page'
		and: '>the component is on the showcase page'
		def selector = "#link_11"

		when: 'I am in the component showcase page'
		setWindowSizeXLG()
		waitForAuthorPreviewPage()

		then: 'The component should appear on the page'
		def component = waitForComponent(selector)

		then: 'It should match reference image.'
		designRef(selector)

	}

	@Unroll("Appearance of Link on charcoal background with default variant secondary outline charcoal on Large Desktop")
	def "Appearance of Link on charcoal background with default variant secondary outline charcoal on Large Desktop"() {

		given: '>I am in the component showcase page'
		and: '>the component is on the showcase page'
		def selector = "#link_12"

		when: 'I am in the component showcase page'
		setWindowSizeXLG()
		waitForAuthorPreviewPage()

		then: 'The component should appear on the page'
		def component = waitForComponent(selector)

		then: 'It should match reference image.'
		designRef(selector)

	}

	@Unroll("Appearance of Link on charcoal background with default variant secondary charcoal on Large Desktop")
	def "Appearance of Link on charcoal background with default variant secondary charcoal on Large Desktop"() {

		given: '>I am in the component showcase page'
		and: '>the component is on the showcase page'
		def selector = "#link_13"

		when: 'I am in the component showcase page'
		setWindowSizeXLG()
		waitForAuthorPreviewPage()

		then: 'The component should appear on the page'
		def component = waitForComponent(selector)

		then: 'It should match reference image.'
		designRef(selector)

	}

	@Unroll("Appearance of Link on charcoal background with default variant disabled outline on Large Desktop")
	def "Appearance of Link on charcoal background with default variant disabled outline on Large Desktop"() {

		given: '>I am in the component showcase page'
		and: '>the component is on the showcase page'
		def selector = "#link_14"

		when: 'I am in the component showcase page'
		setWindowSizeXLG()
		waitForAuthorPreviewPage()

		then: 'The component should appear on the page'
		def component = waitForComponent(selector)

		then: 'It should match reference image.'
		designRef(selector)

	}

	@Unroll("Appearance of Link on charcoal background with default variant primary red on Large Desktop")
	def "Appearance of Link on charcoal background with default variant primary red on Large Desktop"() {

		given: '>I am in the component showcase page'
		and: '>the component is on the showcase page'
		def selector = "#link_15"

		when: 'I am in the component showcase page'
		setWindowSizeXLG()
		waitForAuthorPreviewPage()

		then: 'The component should appear on the page'
		def component = waitForComponent(selector)

		then: 'It should match reference image.'
		designRef(selector)

	}

}
