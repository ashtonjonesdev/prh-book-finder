# PCG Android Developer Take Home Interview

As a part of the interview process, candidates may be asked to complete a sample application in a reasonable amount of time to demonstrate competency in Android application development. The sample application entails consuming the [Penguin Random House Rest api](http://www.penguinrandomhouse.biz/webservices/rest/ "RHRS Documentation") and displaying the data on various screens. A list of the application's requirements and acceptance criteria is provided to guide your development. Additionally, for your convenience, sample API calls and the resulting JSON have been provided as an example. Just because we provide a webservice example does not necessarily mean you have to use one in every situation depending on how you choose to implement the project. For more information on the Penguin Random House Rest api, please read the documentation found [here](http://www.penguinrandomhouse.biz/webservices/rest/ "RHRS Documentation").

The candidate is free to use any libraries or archtiectural design patterns they feel will help them complete the app. Also, design is completely up to the developer. To turn in the project please open a pull request on the repository.

**Requirements**

A.  As a user I need to be able to search an author's name because I am interested in reading that authors books.

  * **Acceptance Criteria**

    *   There is an input for a user to enter data
    *   There is a button to initiate a search
    *   If the input has an entry, pressing the button triggers the webservice
    *   If the input is empty the user is notified that they have to enter valid input
    *   **Example API call**
      *     https://reststop.randomhouse.com/resources/authors?firstName=John&lastName=Grisham
      *     Returns a list of authors
      *     Basic JSON structure:
```json
      {
        "@uri": "https://reststop.randomhouse.com/resources/authors?firstName=John&lastName=Grisham",
        "author": [
            {
                "@uri": "https://reststop.randomhouse.com/resources/authors/11178",
                "approved": "X",
                "authordisplay": "John Grisham",
                "authorfirst": "John",
                "authorfirstlc": "john",
                "authorid": "11178",
                "authorlast": "Grisham",
                "authorlastfirst": "GRISHAM, JOHN",
                "authorlastlc": "grisham",
                "titles": {
                    "isbn": []
                } ...,
                "lastinitial": "g",
                "photocredit": "Â© Charlotte Graham",
                "photodate": "2018",
                "spotlight": "John Grisham is the author of thirty novels, one work of nonfiction, a collection of stories, and six novels for young readers.",
                "works": {
                    "works": []
                }...
            }, ... ANOTHER AUTHOR OBJECT
        ]
      }
```


B.  As a user I need to see my search results as list because there may be more than one author returned by my search.

*   **Acceptance Criteria**

  *     Upon a successful search, each author returned by the webservice will have their full name displayed in its own cell
  *     **Example API call**
      *     https://reststop.randomhouse.com/resources/authors/11178
      *     Returns single author
      *     Basic JSON structure:
```json
      {
        "@uri": "https://reststop.randomhouse.com/resources/authors/11178",
        "approved": "X",
        "authordisplay": "John Grisham",
        "authorfirst": "John",
        "authorfirstlc": "john",
        "authorid": "11178",
        "authorlast": "Grisham",
        "authorlastfirst": "GRISHAM, JOHN",
        "authorlastlc": "grisham",
        "titles": {
            "isbn": [
                {
                    "@contributortype": "A",
                    "$": "9780142417225"
                },
                ...
            ]
        },
        "lastinitial": "g",
        "photocredit": "Â© Charlotte Graham",
        "photodate": "2018",
        "spotlight": "John Grisham is the author of thirty novels, one work of nonfiction, a collection of stories, and six novels for young readers.",
        "works": {
            "works": [
                "72131",
                ...
            ]
        }
    }
```

C.  As a user I want to see an author's details and works if I tap an author's cell because I want to know more about the author and maybe read one of their books.

*   **Acceptance Criteria**

  *     If I tap on an author's cell I should navigate to an Author Detail screen.
  *     The Author detail screen will display the author's full name and spotlight
  *     A list of the author's work(books) will be displayed in a list below the author's details
  *     A book will be represented by the book's simple web title (See JSON object titleweb)
  *     **Example API call**
      *     https://reststop.randomhouse.com/resources/works/72131
      *     returns a single work (book)
      *     basic JSON structure
```json
{
    "@uri": "https://reststop.randomhouse.com/resources/works/72131",
    "authorweb": "GRISHAM, JOHN",
    "titles": {
        "isbn": [
            {
                "@formatcode": "MM",
                "$": "9780440237228"
            },
            {
                "@formatcode": "EL",
                "$": "9780307576040"
            },
            {
                "@formatcode": "DN",
                "$": "9780553753578"
            },
            {
                "@formatcode": "MM",
                "$": "9780345532046"
            },
            {
                "@formatcode": "TR",
                "$": "9780385337939"
            },
            {
                "@formatcode": "DN",
                "$": "9781415947555"
            },
            {
                "@formatcode": "DN",
                "$": "9780553753592"
            },
            {
                "@formatcode": "HC",
                "$": "9780385501200"
            }
        ]
    },
    "onsaledate": "2001-02-06T00:00:00-05:00",
    "rgabout": "Beautifully evoking an extraordinary time and place, A PAINTED HOUSE<i> </i>has captivated millions of readers. Depicting aspects of family, community, trust, and faith through the eyes of a charming little boy, the book makes a memorable choice for reading groups. The questions, discussion topics, and author biography that follow are intended to enhance your reading of John Grisham&#8217;s A PAINTED HOUSE. We hope they will enrich your experience of this enduring novel.",
    "rgauthbio": "John Grisham was raised in rural Arkansas. He is the author of fifteen <i>New York Times</i> bestsellers.<br><br><br><i>From the Paperback edition.</i>",
    "rgcopy": "For seven-year-old Luke Chandler, 1952 is proving to be a year filled with secrets. Heavily in debt and renting some of the most flood-prone land in Arkansas, his family must do whatever it takes to bring in a good cotton crop this year. But Luke witnesses things that could threaten his family&#8217;s entire community. A forbidden love affair is brewing between two of the Chandlers&#8217; migrant workers. Two brutal murders are committed. A fatherless baby is born. And someone has secretly begun painting the Chandlers&#8217; dilapidated farmhouse, whose weathered clapboards make Luke&#8217;s mother look wistfully on the missed opportunities of life.",
    "rgdiscussion": "<p>1. Luke Chandler is exposed to events that many adults have never even seen. What is the effect of reading about these circumstances&#8212;from a difficult childbirth to the possibility of financial ruin&#8212;through the eyes of a seven-year-old narrator?</p><p>2. The Chandlers cannot afford some of the hallmarks of the1950s American dream, such as a television set or a stylish-looking car. Yet other aspects of that time period, such as the Korean War, make an unmistakable impression on them. How does the Chandler household measure up to your own memories or impressions of that era?</p><p>3. Several generations of women are presented in A PAINTED HOUSE, including Gran, Luke&#8217;s mother, and Tally. How do contemporary women compare to those three characters?</p><p>4. Baseball is a central theme in the novel, providing Luke with heroes, dreams, and a diversion from the exhaustion of picking cotton. When the Arkansans challenge the Mexicans to a baseball game, however, Luke sees a darker side to competition. In what way does this scene foreshadow the conclusion of the novel?</p><p>5. How might the novel have been different if Luke&#8217;s father or mother had narrated it?</p><p>6. How does your opinion of Cowboy change throughout the novel? What do you think attracts Tally to him? How did you react to his final showdown with Hank?</p><p>7. Discuss the role of Ricky in A PAINTED HOUSE. Though we never meet him directly, he does play a key part in the progress of the plot. What is the effect of his absence, and the letter writing it inspires? In what way does his experience differ from that of modern soldiers?</p><p>8. What keeps Pappy from giving up on farming?</p><p>9. What role do the Methodist and Baptist churches play in the Black Oak community? How well do religious teachings serve Luke during 1952?</p><p>10. In what way is Black Oak a snapshot of the world at large?</p><p>11. Luke says that most members of his community are descended from Scotch-Irish immigrants. What are some of the legacies of this ancestry?</p><p>12. The weather is a powerful force in A PAINTED HOUSE; floods, heat, hail, and tornadoes all add suspense to the novel. What is it like for the Chandlers to live at the complete mercy of the weather? How is their situation different from that of the cousins who perform indoor industrial work up north? What are the costs and benefits of relying on the natural world for your livelihood?</p><p>13. At the end of the novel, Luke and his parents become migrant workers themselves, venturing off to a new part of the country solely for employment opportunities. Twenty-first-century workers are often asked to transfer to a new part of the globe in order to further their careers. What is the best way to make decisions between financial security and family or cultural ties?</p><p>14. Poverty is a highly relative concept in A PAINTED HOUSE. Though they have no indoor plumbing and have perilously high debts, the Chandlers nonetheless give generously to those in need. How do you define &#8220;rich&#8221; and &#8220;poor&#8221;?</p><p>15. The Chandler house itself conveys a meaningful message. What is the significance of the way in which it gets painted? Do you believe that Pappy really does finish the job after Luke and his family leave? What is the effect of that detail? What causes Luke to set aside his dream of ordering a Cardinals jacket and instead use his meager earnings to buy paint?</p><p>16. In terms of plot and writing style, are any elements of John Grisham&#8217;s legal thrillers evident in A PAINTED HOUSE?</p><p>17. Discuss your own coming-of-age story. What are your first memories of home? Who were the first people you loved?</p><p>18. A PAINTED HOUSE ends with tantalizing possibilities. Speculate about how Luke&#8217;s life unfolds after his family leaves the Arkansas Delta.</p>",
    "titleAuth": "A Painted House : John Grisham",
    "titleSubtitleAuth": "A Painted House : A Novel : John Grisham",
    "titleshort": "PAINTED HOUSE (LIB)(LDL)",
    "titleweb": "A Painted House",
    "workid": "72131"
}
```

D.  As a user I need to be able to navigate back to any screen I have previously been because I may want to visit another author's detail screen from my previous search or search for a new author altogether.

*   **Acceptance Criteria**

  *     There should be a navigation indicator on every interior screen.
  *     Pressing the navigation indicator on the Author Detail should return the user to the Author List screen.
  *     Pressing the navigation indicator on the Author List Screen should return the user to the Search screen.