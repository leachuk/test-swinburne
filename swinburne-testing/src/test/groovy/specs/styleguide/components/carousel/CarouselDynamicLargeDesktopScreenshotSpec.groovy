package specs.styleguide.components.carousel

import support.ComponentSpec

class CarouselDynamicLargeDesktopScreenshotSpec extends ComponentSpec {
    String pathPage = "styleguide/components/carousel"
    String pathSite = "content/swinburne-showcase"
    String language = "en"
    String componentPath = "jcr:content/article/par"

    def setupSpec() {
        loginAsAdmin()
    }

    def "Appearance of Dynamic Carousel (Page List) on large desktop"() {
        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#contentblock_carousel_pagelist_default_short .list"
        setWindowSizeXLG()
        waitForAuthorPreviewPage()

        when: 'The component appears as a carousel on the page'
        waitForComponent(selector)

        then: 'It should match reference image.'
        designRef(selector)
    }

    def "Appearance of Dynamic Carousel (News List) on large desktop"() {
        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#contentblock_carousel_newslist_default_short .list"

        when: 'The component appears as a carousel on the page'
        waitForComponent(selector)

        then: 'It should match reference image.'
        designRef(selector)
    }
}
