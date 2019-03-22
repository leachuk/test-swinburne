package specs.styleguide.components.carousel

import support.ComponentSpec

class CarouselDynamicScreenshotSpec extends ComponentSpec {
    String pathPage = "styleguide/components/carousel"
    String pathSite = "content/swinburne-showcase"
    String language = "en"
    String componentPath = "jcr:content/article/par"

    def setupSpec() {
        loginAsAdmin()
    }

    def "Appearance of Dynamic Carousel (Page List) on mobile"() {
        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#contentblock_carousel_pagelist_default_short .owl-carousel.owl-loaded"

        when: 'I am in the component showcase page'
        setWindowSizeSM()
        waitForAuthorPreviewPage()

        then: 'The component should appear as a carousel on the page'
        waitForComponent(selector)

        then: 'It should match reference image.'
        designRef(selector)
    }

    def "Appearance of Dynamic Carousel (Page List) on tablet"() {
        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#contentblock_carousel_pagelist_default_short .owl-carousel.owl-loaded"

        when: 'I am in the component showcase page'
        setWindowSizeMD()
        waitForAuthorPreviewPage()

        then: 'The component should appear as a carousel on the page'
        waitForComponent(selector)

        then: 'It should match reference image.'
        designRef(selector)
    }

    def "Appearance of Dynamic Carousel (Page List) on small desktop"() {
        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#contentblock_carousel_pagelist_default_short .list"

        when: 'I am in the component showcase page'
        setWindowSizeLG()
        waitForAuthorPreviewPage()

        then: 'The component should appear as a normal list on the page'
        waitForComponent(selector)

        then: 'It should match reference image.'
        designRef(selector)
    }

    def "Appearance of Dynamic Carousel (Page List) on large desktop"() {
        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#contentblock_carousel_pagelist_default_short .list"

        when: 'I am in the component showcase page'
        setWindowSizeXLG()
        waitForAuthorPreviewPage()

        then: 'The component should appear as a normal list on the page'
        waitForComponent(selector)

        then: 'It should match reference image.'
        designRef(selector)
    }

    def "Appearance of Dynamic Carousel (News List) on mobile"() {
        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#contentblock_carousel_newslist_default_short .owl-carousel.owl-loaded"

        when: 'I am in the component showcase page'
        setWindowSizeSM()
        waitForAuthorPreviewPage()

        then: 'The component should appear as a carousel on the page'
        waitForComponent(selector)

        then: 'It should match reference image.'
        designRef(selector)
    }

    def "Appearance of Dynamic Carousel (News List) on tablet"() {
        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#contentblock_carousel_newslist_default_short .owl-carousel.owl-loaded"

        when: 'I am in the component showcase page'
        setWindowSizeMD()
        waitForAuthorPreviewPage()

        then: 'The component should appear as a carousel on the page'
        waitForComponent(selector)

        then: 'It should match reference image.'
        designRef(selector)
    }

    def "Appearance of Dynamic Carousel (News List) on small desktop"() {
        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#contentblock_carousel_newslist_default_short .list"

        when: 'I am in the component showcase page'
        setWindowSizeLG()
        waitForAuthorPreviewPage()

        then: 'The component should appear as a normal list on the page'
        waitForComponent(selector)

        then: 'It should match reference image.'
        designRef(selector)
    }

    def "Appearance of Dynamic Carousel (News List) on large desktop"() {
        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#contentblock_carousel_newslist_default_short .list"

        when: 'I am in the component showcase page'
        setWindowSizeXLG()
        waitForAuthorPreviewPage()

        then: 'The component should appear as a normal list on the page'
        waitForComponent(selector)

        then: 'It should match reference image.'
        designRef(selector)
    }
}
