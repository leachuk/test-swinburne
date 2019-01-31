package specs.component.lists.newslist

import spock.lang.Stepwise
import spock.lang.Unroll
import support.ComponentSpec

import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Stepwise
class NewsListPublishSpec extends ComponentSpec {

    String pathPage = "component/lists/news-list"
    String pathSite = "content/swinburne-showcase"
    String language = "en"
    String componentPath = "jcr:content/article/par/contentblock/par/newslist"

    @Unroll("Functionality of News List Component default in #viewport.label")
    def "Functionality of News List Component default"() {

        given: '>the page hierarchy is created as "Components" > "Lists" > "News List"'
        and: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#newslist_RGZ6WGQKB"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The component should be on the page"
        def component = waitForComponent(selector)

        and: "The cards should have a title"
        def containsTitle = true
        assert  $("${selector} > div.content > ul > li").each { element ->
            if(!$(element.find("div > div.card-body > h3")).text().contains("News Details Demo Title")){
                containsTitle = false
            }
        }
        assert containsTitle == true

        and: "The cards should have tags"
        def containsTags = true
        assert  $("${selector} > div.content > ul > li").each { element ->
            if($(element.find("div > div.card-body > div.card-taglist > ul > li")).size() != 2){
                containsTags = false
            }
        }
        assert containsTags == true

        and: "The cards should have a clickable link"
        def containsLink = true
        assert  $("${selector} > div.content > ul > li").each { element ->
            if(!$(element.find("div > div.card-body > a")).text().contains("Read full post")){
                containsLink = false
            }
        }
        assert containsLink == true

        and: "The cards should have a date in EEEE DD MMMM YYYY format format"
        def containsDateFormat = true
        assert  $("${selector} > div.content > ul > li").each { element ->
            def dateString = $(element.find("div > div.card-body > p")).text()
            DateTimeFormatter format = DateTimeFormatter.ofPattern("EEEE dd MMMM yyyy", Locale.ENGLISH)
            if(!LocalDate.parse(dateString, format)){
                containsDateFormat = false
            }
        }
        assert containsDateFormat == true

        and: "The cards should have an image"
        def containsImage = true
        assert  $("${selector} > div.content > ul > li").each { element ->
            if(!$(element.find("div > img.card-img-top")).attr("src").contains("city2.jpg")){
                containsImage = false
            }
        }
        assert containsImage == true

        where:
        viewport << getViewPorts()
    }

    def setupSpec() {
        loginAsAdmin()
    }

}
