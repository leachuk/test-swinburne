package specs.styleguide.components.carousel

import spock.lang.Stepwise
import spock.lang.Unroll
import support.ComponentSpec

@Stepwise
class CarouselPublishSpec extends ComponentSpec {
    String pathPage = "styleguide/components/carousel"
    String pathSite = "content/swinburne-showcase"
    String language = "en"
    String componentPath = "jcr:content/article/par"

    def setupSpec() {
        loginAsAdmin()
    }

    @Unroll("Basic Carousel - Default Background (Page List) in #viewport.label")
    def "Basic Carousel - Default Background (Page List)"() {
        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#contentblock_carousel_pagelist_default"
        def selectorCarousel = "${selector} .owl-carousel.owl-loaded"

        when: 'I am in the component showcase page'
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: 'The component should appear on the page'
        waitForComponent(selectorCarousel)
        takeScreenshot($(selectorCarousel).firstElement(), "The component should be on the page")

        and: 'Has six items'
        assert $("${selectorCarousel} .owl-item").size() == 6

        and: 'First item is active'
        assert $("${selectorCarousel} .owl-stage").children().firstElement().getAttribute("class").contains("active")

        and: 'Clicking the next button progresses the carousel'
        $("${selectorCarousel} .owl-next").click()

        and: 'The first item is no longer active'
        assert !$("${selectorCarousel} .owl-stage").children().firstElement().getAttribute("class").contains("active")

        where:
        viewport << getViewPorts()
    }
}
