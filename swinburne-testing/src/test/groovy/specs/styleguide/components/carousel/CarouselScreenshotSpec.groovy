package specs.styleguide.components.carousel

import spock.lang.Stepwise
import spock.lang.Unroll
import support.ComponentSpec

@Stepwise
class CarouselScreenshotSpec extends ComponentSpec {
    String pathPage = "styleguide/components/carousel"
    String pathSite = "content/swinburne-showcase"
    String language = "en"
    String componentPath = "jcr:content/article/par"

    def setupSpec() {
        loginAsAdmin()
    }

    @Unroll("Appearance of Basic Carousel Default Background Page List in #viewport.label")
    def "Appearance of Basic Carousel Default Background Page List"() {
        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#contentblock_carousel_pagelist_default_list"
        def selectorCarousel = "${selector} .owl-carousel.owl-loaded"

        when: 'I am in the component showcase page'
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: 'The component should appear on the page'
        waitForComponent(selector)

        then: 'It should match reference image.'
        designRef(selector)

        where:
        viewport << getViewPorts()
    }

    @Unroll("Appearance of Basic Carousel Grey Background Page List in #viewport.label")
    def "Appearance of Basic Carousel Grey Background Page List"() {
        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#contentblock_carousel_pagelist_grey_list"
        def selectorCarousel = "${selector} .owl-carousel.owl-loaded"

        when: 'I am in the component showcase page'
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: 'The component should appear on the page'
        waitForComponent(selector)

        then: 'It should match reference image.'
        designRef(selector)

        where:
        viewport << getViewPorts()
    }

    @Unroll("Appearance of Basic Carousel Yellow Background Page List in #viewport.label")
    def "Appearance of Basic Carousel Yellow Background Page List"() {
        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#contentblock_carousel_pagelist_yellow_list"
        def selectorCarousel = "${selector} .owl-carousel.owl-loaded"

        when: 'I am in the component showcase page'
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: 'The component should appear on the page'
        waitForComponent(selector)

        then: 'It should match reference image.'
        designRef(selector)

        where:
        viewport << getViewPorts()
    }

    @Unroll("Appearance of Basic Carousel Default Background News List in #viewport.label")
    def "Appearance of Basic Carousel Default Background News List"() {
        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#contentblock_carousel_newslist_default_list"
        def selectorCarousel = "${selector} .owl-carousel.owl-loaded"

        when: 'I am in the component showcase page'
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: 'The component should appear on the page'
        waitForComponent(selector)

        then: 'It should match reference image.'
        designRef(selector)

        where:
        viewport << getViewPorts()
    }

    @Unroll("Appearance of Basic Carousel Grey Background News List in #viewport.label")
    def "Appearance of Basic Carousel Grey Background News List"() {
        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#contentblock_carousel_newslist_grey_list"
        def selectorCarousel = "${selector} .owl-carousel.owl-loaded"

        when: 'I am in the component showcase page'
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: 'The component should appear on the page'
        waitForComponent(selector)

        then: 'It should match reference image.'
        designRef(selector)

        where:
        viewport << getViewPorts()
    }

    @Unroll("Appearance of Basic Carousel Yellow Background News List in #viewport.label")
    def "Appearance of Basic Carousel Yellow Background News List"() {
        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#contentblock_carousel_newslist_yellow_list"
        def selectorCarousel = "${selector} .owl-carousel.owl-loaded"

        when: 'I am in the component showcase page'
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: 'The component should appear on the page'
        waitForComponent(selector)

        then: 'It should match reference image.'
        designRef(selector)

        where:
        viewport << getViewPorts()
    }
}
