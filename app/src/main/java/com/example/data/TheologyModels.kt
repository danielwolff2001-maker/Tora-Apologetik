package com.example.data

data class Section(
    val title: String,
    val content: String,
    val highlights: List<String> = emptyList()
)

data class ThemaItem(
    val id: String,
    val title: String,
    val shortTitle: String,
    val category: String,
    val shortDescription: String,
    val durationMinutes: Int,
    val scriptureRefs: String,
    val iconType: String, // "book", "branch", "council", "food", "law", "priesthood", "shadow"
    val objection: String,
    val sections: List<Section>
)

data class FallacyItem(
    val title: String,
    val description: String,
    val example: String,
    val correction: String
)

data class CleanComfortVerse(
    val verse: String,
    val text: String,
    val category: String, // "Trauma-Heilung", "Glaubenszweifel", "Geistlicher Missbrauch", "Tora-Freude"
    val devotion: String
)

data class QuizQuestion(
    val id: Int,
    val question: String,
    val options: List<String>,
    val correctIndex: Int,
    val explanation: String,
    val difficulty: String // "Einsteiger", "Mittel", "Experte"
)

object TheologyData {
    val FALLACIES = listOf(
        FallacyItem(
            title = "Strohmann-Argument (Straw Man)",
            description = "Die Position des Tora-Befolgers wird verzerrt oder übertrieben dargestellt, um sie leichter angreifen zu können.",
            example = "Einwand: 'Ihr glaubt also, dass man sich durch das Halten des Sabbats und das Meiden von Schweinefleisch den Weg in den Himmel erarbeiten oder verdienen kann.'",
            correction = "Richtigstellung: Nein. Ausnahmslos jede Erlösung geschieht durch reines Vertrauen auf das Opfer des Messias aus bloßer Gnade. Der Gehorsam gegenüber der Tora ist nicht die *Wurzel* der Errettung, sondern ihre *Frucht* und eine freudige Antwort darauf."
        ),
        FallacyItem(
            title = "Zirkelschluss (Circular Reasoning)",
            description = "Die Annahme, die bewiesen werden soll, wird bereits im Vorheld als unumstößliche Prämisse vorausgesetzt.",
            example = "Einwand: 'Das Gesetz ist für Christen ungültig, weil Jesus das Gesetz abgeschafft hat, weshalb wir es nicht mehr halten müssen.'",
            correction = "Richtigstellung: Hier wird die Behauptung als Beweis genutzt. Matthäus 5:17-19 beweist das exakte Gegenteil. Wir müssen jede Schriftstelle einzeln exegetisch im historischen Kontext prüfen."
        ),
        FallacyItem(
            title = "Äquivokation (Wortglauberei & Verschiebung)",
            description = "Derselbe Begriff wird in verschiedenen Teilen der Argumentation mit völlig unterschiedlichen Bedeutungen verwendet.",
            example = "Einwand: 'Paulus schreibt, wir sind nicht unter dem Gesetz. Das bedeutet, das Gesetz ist für uns außer Kraft gesetzt.'",
            correction = "Richtigstellung: Paulus benennt mindestens 7 verschiedene 'Gesetze' (z.B. Gesetz der Sünde, Gesetz des Glaubens, Gesetz Gottes). 'Unter dem Gesetz' (hypo nomon) bedeutet im paulinischen Sprachgebrauch unter der Verurteilung und Verdammnis des Gesetzes aufgrund von Sünde zu stehen."
        ),
        FallacyItem(
            title = "Anachronismus (Kulturelle Rückprojizierung)",
            description = "Moderne theologische Kategorien oder Begrifflichkeiten werden unkritisch in das erste Jahrhundert zurückprojiziert.",
            example = "Einwand: 'Markus 7:19 beweist, dass Schweinefleisch nun reine Nahrung ist, weil dort steht: So erklärte er alle Speisen für rein.'",
            correction = "Richtigstellung: Zu Jesu Zeiten war für jeden Israeliten 'Speise' ausschließlich durch Leviticus 11 definiert. Schweinefleisch war begrifflich keine Nahrung. Der Streit ging um rituelle Händewaschungstraditionen (koinos Hände), nicht um Tora-Speisevorschriften (akathartos)."
        ),
        FallacyItem(
            title = "Aus dem Schweigen argumentieren (Silentium)",
            description = "Aus der Tatsache, dass das Neue Testament etwas nicht explizit wiederholt, wird fälschlich geschlossen, dass es ungültig sei.",
            example = "Einwand: 'Das Verbot von Bestialität oder Götzenbildern wird im Neuen Testament kaum wiederholt, also gelten die alttestamentlichen Vorschriften im Ganzen nicht mehr.'",
            correction = "Richtigstellung: Das Neue Testament bestätigt das Alte Testament im Ganzen. Wenn ein Gebot nicht wiederholt wird, gilt es unberührt fort. Nur explizite Änderungen (wie die Verlagerung des Priestertums in den Himmel) bedürfen exegetischer Erläuterung."
        ),
        FallacyItem(
            title = "Die falsche Dichotomie von 'Buchstabe vs. Geist'",
            description = "Der Geist eines Gebots wird rücksichtslos gegen seine physische Erfüllung ausgespielt, um Gehorsam als legalistisch zu diffamieren.",
            example = "Einwand: 'Paulus schreibt, dass der Buchstabe tötet, aber der Geist lebendig macht. Wir müssen den Sabbat also nur noch geistlich im Fleiße unseres Herzens ruhen lassen, nicht mehr physisch.'",
            correction = "Richtigstellung: Der 'Geist' eines Gesetzes ist seine höchste Absicht und Motivation (Liebe), die den 'Buchstaben' (das geschriebene Wort) niemals vernichtet, sondern ihn erst lebendig und tatenreich ausfüllt. Wenn der Buchstabe sagt 'Du sollst nicht stehlen', dann bedeutet der 'Geist' des Gesetzes nicht, dass man nun physisch stehlen darf, solange das Herz rein ist. Der Geist befähigt uns, das Gebot noch tiefer physisch und geistig einzuhalten."
        ),
        FallacyItem(
            title = "Generalisierung der 'Beschneidung' (Galater-Falle)",
            description = "Die antike Beschneidungsdebatte (Konversion zur Rettung) wird fälschlicherweise auf alle biblischen Lebensgebote übertragen.",
            example = "Einwand: 'Paulus verbietet in Galater 5 die Beschneidung, daher sind alle Gebote (wie Bundessabbat und Nahrungstrennung) heute ebenfalls verboten.'",
            correction = "Richtigstellung: Paulus bekämpfte das rituelle Abschließen einer nationalen Pharisäer-Konversion zum Zwecke der Selbstrechtfertigung ('um gerettet zu werden', Apg 15:1). Er selbst ließ den Heidenchristen Timotheus beschneiden (Apg 16:3) und schrieb in 1. Korinther 7:19: 'Die Beschneidung ist nichts, und das Unbeschnittensein ist nichts, sondern das Halten der Gebote Gottes.' Er trennte also theologische Konversionsrituale messerscharf vom lebenspendenden Tora-Gehorsam."
        )
    )

    val COMFORT_VERSES = listOf(
        CleanComfortVerse(
            verse = "Psalm 119:44-45",
            text = "Ich will dein Gesetz stets bewahren, immer und ewiglich. Und ich werde wandeln in weitem Raum; denn ich suche deine Befehle.",
            category = "Geistlicher Missbrauch",
            devotion = "Viele Menschen erfahren in legalistischen oder autoritären Gemeinden geistlichen Missbrauch. Doch Gottes Gesetz ist kein sklavenhaftes Joch der Unterdrückung, sondern der Weg echter Freiheit und des weiten Raumes. Es schützt uns vor der Willkür menschlicher Zusatzregeln."
        ),
        CleanComfortVerse(
            verse = "1. Johannes 5:3",
            text = "Denn das ist die Liebe zu Gott, dass wir seine Gebote halten; und seine Gebote sind nicht schwer.",
            category = "Trauma-Heilung",
            devotion = "Wenn dir beigebracht wurde, dass das Halten von Gottes Sabbat oder Speisegeboten eine unerträgliche Last ist, erinnert Johannes dich an die Wahrheit des Messias. Gebote aus Liebe zu befolgen heilt das Herz und führt uns in eine innige, unbeschwerte Gemeinschaft mit dem Vater."
        ),
        CleanComfortVerse(
            verse = "Psalm 119:97",
            text = "Wie habe ich dein Gesetz so lieb! Täglich sinne ich ihm nach.",
            category = "Tora-Freude",
            devotion = "In einer Glaubenskrise bricht oft das Vertrauen in menschliche Traditionen zusammen. Das ist eine Einladung, zurück zum ewigen, unveränderlichen Fundament des Wortes Gottes zu kehren. Hier findest du unerschütterliche Gewissheit."
        ),
        CleanComfortVerse(
            verse = "Jesaja 40:8",
            text = "Das Gras verdorrt, die Blume ist abgefallen; aber das Wort unseres Gottes bleibt in Ewigkeit!",
            category = "Glaubenszweifel",
            devotion = "Systeme kommen und gehen, Modetrends in der Theologie verblühen wie Gras. Doch Gottes Weisung (Tora) steht fest wie ein Felsen. Wenn dein Fundament wankt, klammere dich an sein ewiges und treues Zeugnis."
        ),
        CleanComfortVerse(
            verse = "Matthäus 11:28-30",
            text = "Kommt her zu mir alle, die ihr mühselig und beladen seid; ich will euch erquicken ... Denn mein Joch ist sanft, und meine Last ist leicht.",
            category = "Glaubenszweifel",
            devotion = "Jeschua kritisiert unbarmherzige, von Menschen erfundene Auslegungen (Pharisäerrituale), welche die Tora verfälschen und aufladen. Er lädt uns ein, sein sanftes, tora-gemäßes Joch der Liebe zu tragen, welches wirkliche Seelenruhe spendet."
        ),
        CleanComfortVerse(
            verse = "Psalm 119:165",
            text = "Großen Frieden haben, die dein Gesetz lieben; und sie erleiden keinen Anstoß.",
            category = "Trauma-Heilung",
            devotion = "Wenn du durch religiösen Zwang oder heuchlerische Menschen verletzt wurde, trenne die menschlichen Taten von Gottes ursprünglicher Absicht. Seine Tora zu lieben bringt tiefe, innige Genesung und schützt dein Herz vor Enttäuschungen."
        ),
        CleanComfortVerse(
            verse = "Jesaja 56:6-7",
            text = "Und die Fremdlinge, die sich dem HERRN anschließen, um ihm zu dienen ... und die alle den Sabbat festhalten ... die will ich zu meinem heiligen Berg bringen und sie erfreuen in meinem Bethaus.",
            category = "Tora-Freude",
            devotion = "Gott verspricht ausdrücklich allen Nicht-Israeliten (Fremdlingen), die Seinen Bund lieben und den Sabbat ehren, tiefste Freude und Annahme in Seiner Gegenwart. Du bist hier kein Fremdkörper, sondern ein geliebter Eingebürgerter."
        )
    )

    val THEMEN = listOf(
        ThemaItem(
            id = "matthaeus_5_17",
            title = "Matthäus 5:17 - Abgeschafft oder Erfüllt?",
            shortTitle = "Matthäus 5:17",
            category = "Grundlagen",
            shortDescription = "Was bedeutet es, dass Jeschua das Gesetz erfüllt hat? Ein genauer Blick auf das Wort 'pleroo'.",
            durationMinutes = 15,
            scriptureRefs = "Mt 5:17-19, Mt 3:15",
            iconType = "law",
            objection = "Jesus hat gesagt, er sei gekommen um das Gesetz zu erfüllen. 'Erfüllen' bedeutet doch 'zu Ende bringen' oder 'abschaffen durch Vollendung'. Da er es erfüllt hat, müssen wir die Gebote nicht mehr halten.",
            sections = listOf(
                Section(
                    title = "Die falsche Definition von 'Erfüllen'",
                    content = "Erkenne den sprachlichen Widerspruch: Jesus verneint in Vers 17 explizit, dass er zum 'Auflösen' gekommen ist. Wenn 'Erfüllen' dasselbe Ergebnis wie 'Auflösen' zur Folge hätte (nämlich das Ende des Gebots), dann hätte Jesus im selben Satz gesagt: 'Ich bin nicht gekommen um das Gesetz aufzulösen, sondern um es aufzulösen.' Das wäre absurd. 'Auflösen' (griech. kataluo) bedeutet außer Kraft setzen; 'Erfüllen' (griech. pleroo) bedeutet das genaue Gegenteil.",
                    highlights = listOf("kataluo = auflösen, außer Kraft setzen", "pleroo = füllen, zur vollen Entfaltung bringen")
                ),
                Section(
                    title = "Das griechische Wort 'Pleroo' im Kontext",
                    content = "Das Wort 'pleroo' bedeutet wörtlich 'voll machen' oder 'füllen'. In Matthäus 3:15 sagt Jesus bei seiner Taufe, er müsse 'alle Gerechtigkeit erfüllen'. Bedeutet das, dass Gerechtigkeit danach abgeschafft wurde? Natürlich nicht! Ebenso bedeutet das Erfüllen der Freude (Römer 15:13) nicht das Abschaffen der Freude. Jesus hat das Gesetz erfüllt, indem er uns zeigte, wie man es vollkommen lebt, und es mit seinem Geist in unsere Herzen schreibt.",
                    highlights = listOf("Ausleben als Vorbild", "Kein Jota geht verloren vor dem Ende dieser Schöpfung")
                ),
                Section(
                    title = "Bis Himmel und Erde vergehen",
                    content = "In Matthäus 5:18 setzt der Messias eine unmissverständliche zeitliche Grenze: 'Bis Himmel und Erde vergehen, wird nicht ein einziges Strichlein vom Gesetz vergehen, bis alles geschieht.' Da wir heute noch auf derselben Erde stehen und unter demselben Himmel leben, ist kein einziges Gebot vergangen. Die Erfüllung am Kreuz hat das Gesetz nicht obsolet gemacht, sondern bewiesen, dass es heilig und gerecht ist."
                )
            )
        ),
        ThemaItem(
            id = "olbaum_israel",
            title = "Der Ölbaum Israels - Eingepfropft",
            shortTitle = "Der Ölbaum Israels",
            category = "Israel & die Heiden",
            shortDescription = "Heiden werden in die Bürgerschaft Israels aufgenommen. Was bedeutet das für das Tora-Verständnis?",
            durationMinutes = 18,
            scriptureRefs = "Röm 11:17-24, Eph 2:11-19",
            iconType = "branch",
            objection = "Die Tora gilt nur für das physische Volk Israel. Als Christen gehören wir zur Gemeinde (Kirche), einer separaten geistlichen Einheit, die nicht dem mosaischen Gesetz unterworfen ist.",
            sections = listOf(
                Section(
                    title = "Ein gemischtes Volk von Anfang an",
                    content = "Schon beim Auszug aus Ägypten (Exodus) zog ein großes 'Mischvolk' mit den leiblichen Nachkommen Jakobs aus (2. Mose 12:37-38). Am Berg Sinai standen Heiden und Hebräer zusammen und sprachen einmütig: 'Alles, was der HERR gesagt hat, wollen wir tun.' Gott hat nie zwei getrennte Völker gewollt. Fremdlinge, die den Gott Israels anbeten, waren ausdrücklich Teil des Bundes und hielten die Sabbatruhe (Jesaja 56:6-7) und die Feste.",
                    highlights = listOf("Einführung des Mischvolks", "Gleiches Recht für Inländer und Fremdlinge")
                ),
                Section(
                    title = "Das Gleichnis vom Ölbaum in Römer 11",
                    content = "Paulus erklärt Heidenchristen, dass sie wie wilde Ölzweige in den edlen Kulturölbaum Israels eingepfropft wurden. Sie nähren sich nun von der fetten Wurzel Israels. Sie bilden keinen neuen Baum, sondern teilen die Identität Israels. Wenn du ein Teil Israels bist, teilst du auch die Segnungen und Pflichten des Bundes – einschließlich Gottes Tora, die die weise Anleitung dieses Lebensstils ist.",
                    highlights = listOf("Es gibt nur einen edlen Ölbaum", "Der Fremdling ist Mitbürger der Heiligen")
                ),
                Section(
                    title = "Keine Trennung: Ekklesia und Israel",
                    content = "Das griechische Wort 'ekklesia' (oft als Kirche übersetzt) bedeutet einfach 'Versammlung'. In der Septuaginta (griechisches Altes Testament) wird damit regelmäßig die 'Gemeinde in der Wüste' bezeichnet (Apg 7:38). Der Neue Bund ist kein neuer Bundespartner, sondern Gott schreibt laut Jeremia 31:31 und Hebräer 8:8 die Tora in die Herzen des Hauses Israel und des Hauses Juda. Durch Christus hast du das Bürgerrecht Israels erlangt."
                )
            )
        ),
        ThemaItem(
            id = "markus_7",
            title = "Markus 7 - Sind alle Speisen rein?",
            shortTitle = "Markus 7",
            category = "Speisegebote",
            shortDescription = "Was bedeutet das griechische Wort 'koinos'? Die Kontroverse über die rituelle Waschung der Hände.",
            durationMinutes = 15,
            scriptureRefs = "Mk 7:1-23, Mt 15:1-20",
            iconType = "food",
            objection = "In Markus 7:19 steht deutlich geschrieben: 'Damit erklärte er alle Speisen für rein.' Das bedeutet, Christen dürfen alles essen, auch Schweinefleisch oder Garnelen. Das Speisegesetz ist abgeschafft.",
            sections = listOf(
                Section(
                    title = "Die historische Streitfrage: Händewaschen",
                    content = "Achte auf den genauen Kontext in Markus 7:1-5: Die Pharisäer beschweren sich nicht darüber, dass die Jünger verbotenes Fleisch (wie Schwein) essen. Sie kritisieren, dass die Jünger das Brot mit 'ungewaschenen Händen' essen. Dies war eine rabbinische Überlieferung (die Tradition der Ältesten), um Speisen vor ritueller Unreinheit (koinos) zu schützen. Es ging nie um die Tora-Vorschriften über reine und unreine Tiere aus 3. Mose 11.",
                    highlights = listOf("Das Problem waren ungewaschene Hände", "Menschengemachte Gebote vs. Gottes Gebote")
                ),
                Section(
                    title = "Koinos versus Akathartos",
                    content = "Das Neue Testament unterscheidet messerscharf zwischen zwei Begriffen: 1) 'Akathartos' (griech. von Natur aus unrein/abscheulich, z.B. Schwein in der Septuaginta). 2) 'Koinos' (griech. gewöhnlich/rituell verunreinigt durch Berührung, aber von Natur aus eigentlich rein). In Markus 7 wird ausschließlich das Wort 'koinos' verhandelt. Ein reines Brot wird laut Pharisäern 'koinos' (unheilig), wenn man es mit schmutzigen Händen anfasst. Jesus widerlegt diese nicht-biblische Tradition.",
                    highlights = listOf("koinos = rituell gemein", "akathartos = biblisch unrein")
                ),
                Section(
                    title = "Der Zusatz 'So erklärte er alle Speisen für rein'",
                    content = "Dieser Satz ist in den ältesten griechischen Handschriften gar nicht enthalten, sondern wurde später von Übersetzern als Interpretation hinzugefügt. Und selbst wenn Jesus es sagte: Für einen Hebräer war 'Speise' (Nahrung) begrifflich nur das, was Gott in 3. Mose 11 als essbar definiert hat. Ein Schwein war niemals Speise. Das Nichtwaschen der Hände macht erlaubte Speise nicht unheilig, das ist die Botschaft."
                )
            )
        ),
        ThemaItem(
            id = "kolosser_2_16",
            title = "Kolosser 2:16 - Richten über Sabbat und Feste",
            shortTitle = "Kolosser 2:16",
            category = "Paulus & die Tora",
            shortDescription = "Sollten wir uns wegen dem Sabbat richten lassen? Ein exegetischer Blick in den griechischen Grundtext.",
            durationMinutes = 20,
            scriptureRefs = "Kol 2:13-17, Gal 4:9-10",
            iconType = "shadow",
            objection = "Paulus schreibt: 'So richte euch nun niemand über Speise oder Trank, oder in Ansehung eines Festes ... oder von Sabbathen.' Sabbat und Feste sind nur ein Schatten, der durch Christus ein Ende fand.",
            sections = listOf(
                Section(
                    title = "Wer richtet hier wen?",
                    content = "Traditionell wird geglaubt, Paulus verbiete das Halten des Sabbats. Doch lies genau: Die kolossische Gemeinde bestand vorwiegend aus bekehrten Heiden (Kol 2:13), die anfingen, den Sabbat und Gottes Feste freudig zu feiern. Die heidnische Kultur und aszetische Philosophen (Gnostiker) kritisierten sie heftig wegen ihres Essens, Trinkens und Feierns (Kol 2:21-22: 'Rühre das nicht an!'). Paulus stärkt ihnen den Rücken: Lasst euch von diesen heidnischen Asketen nicht verurteilen!",
                    highlights = listOf("Asketen lehnten Feiertage ab", "Gemeinde feierte die biblischen Feste")
                ),
                Section(
                    title = "Die verschobene Grammatik: Kai und En Meros",
                    content = "Im Griechischen steht 'kai' (und) anstelle von 'oder' zwischen Speise und Trank. Der Begriff 'en meros' bedeutet 'hinsichtlich eines Teils' oder 'beim Feiern eines Festes'. Es beschreibt, dass Gnostiker die Gläubigen bezüglich ihrer Nahrung *während* der Feste verurteilten. Sie predigten strenge Askese. Paulus warnt davor, deren unbiblische, menschengemachte Philosophie (Kol 2:8) anzunehmen.",
                    highlights = listOf("Askese ist menschliche Philosophie", "Kein Verbot der Sabbatbeobachtung")
                ),
                Section(
                    title = "Der Sabbat als prophetischer Schatten",
                    content = "Paulus schreibt in Vers 17, dass diese Dinge ein Schatten der *zukünftigen* Dinge sind (griech. Präsens: 'sind ein Schatten', nicht 'waren'). Sie weisen prophetisch voraus. Der Sabbat weist auf das kommende 1000-jährige Friedensreich des Messias hin. Ein Schatten verschwindet nicht, wenn der Körper naht, sondern beweist, dass der Körper präsent ist! Wir ehren den Messias, indem wir seine Feste feiern."
                )
            )
        ),
        ThemaItem(
            id = "hebraeer_7_12",
            title = "Hebräer 7:12 - Veränderung im Gesetz",
            shortTitle = "Hebräer 7:12",
            category = "Hebräerbrief",
            shortDescription = "Veränderung des Priestertums - bedeutet das ein Ende der Tora? Die griechischen Begriffe geklärt.",
            durationMinutes = 20,
            scriptureRefs = "Heb 7:11-19, Heb 8:4-5",
            iconType = "priesthood",
            objection = "Der Hebräerbrief sagt: 'Denn wenn das Priestertum verändert wird, so muss notwendigerweise auch eine Änderung des Gesetzes erfolgen.' Das mosaische Gesetz wurde also komplett abgeschafft.",
            sections = listOf(
                Section(
                    title = "Metathesis: Relokation statt Abschaffung",
                    content = "Das griechische Wort für 'Änderung' ist 'metathesis' (von metatithemi). Es bedeutet wörtlich 'Umplatzierung', 'Transfer' oder 'Wechsel des Ortes'. Henoch wurde laut Hebräer 11:5 'entrückt' (metatithemi) – er wurde an einen anderen Ort versetzt, nicht vernichtet! Das Gesetz über das Priestertum wurde nicht abgeschafft, sondern der Dienst wurde von der irdischen Stiftshütte (unvollkommen, Aaroniter) in die himmlische verlagert (vollkommen, Melchisedek).",
                    highlights = listOf("metathesis = Umplatzierung, Transfer von Ort zu Ort", "Das Gesetz gilt weiterhin auf Erden")
                ),
                Section(
                    title = "Das ewig gültige Priestertum Jesu",
                    content = "Jesus stammt nicht aus dem Stamm Levi, sondern aus Juda. Er darf laut Tora auf Erden kein Priester sein (Hebräer 8:4: 'Wenn er nun auf Erden wäre, so wäre er nicht einmal Priester'). Sein Priesteramt ist im Himmel wirksam nach der Ordnung Melchisedeks. Da das himmlische Heiligtum andere Parameter hat, wurde das Gesetz bezüglich des Priesterdienstes re-lokalisiert. Das moralische Gesetz und die Sabbatgebote sind davon völlig unberührt.",
                    highlights = listOf("Jesus ist Priester im Himmel", "Die irdische Tora bleibt unverändert gültig")
                ),
                Section(
                    title = "Opfer als Schatten des Himmels",
                    content = "Tieropfer im irdischen Tempel waren nie dazu gedacht, Sünden ewig wegzunehmen (Hebräer 10:4). Sie reinigten das Fleisch rituell für den Tempelzugang. Das Opfer des Messias reinigt unser Gewissen für das ewige Leben. Erst wenn diese Schöpfung vergeht, vergeht das irdische Zeremoniell – bis dahin weisen beide Ebenen (irdisch und himmlisch) harmonisch auf Gottes Heilsplan hin."
                )
            )
        ),
        ThemaItem(
            id = "paulinisches_paradoxon",
            title = "Das paulinische Paradoxon: Die sieben Gesetze",
            shortTitle = "Die sieben Gesetze",
            category = "Grundlagen",
            shortDescription = "Warum sind Paulus' Briefe so schwer zu verstehen? Eine systematische Analyse von hypo nomon.",
            durationMinutes = 25,
            scriptureRefs = "Röm 6:14, Röm 7:22-25, Gal 3:19",
            iconType = "law",
            objection = "In Römer 6:14 steht wörtlich: 'Denn die Sünde wird nicht herrschen über euch, weil ihr nicht unter dem Gesetz seid, sondern unter der Gnade.' Das beweist doch das Ende der Tora für den Gläubigen.",
            sections = listOf(
                Section(
                    title = "Die sieben differenzierten Gesetze im Römerbrief",
                    content = "Viele Ausleger scheitern, weil sie das Wort 'Gesetz' (griech. nomos) pauschalisieren. Paulus lehrt nachweisbar über mindestens sieben Gesetze: 1) Das Gesetz Gottes (Röm 7:22, Tora), 2) Das Gesetz der Sünde (Röm 7:23), 3) Das Gesetz der Sünde und des Todes (Röm 8:2), 4) Das Gesetz des Geistes des Lebens (Röm 8:2), 5) Das Gesetz des Glaubens (Röm 3:27), 6) Das Gesetz der Rechtschaffenheit (Röm 9:31), und 7) Das Gesetz Christi (1. Kor 9:21). Wenn wir nicht trennen, verfallen wir in Gesetzlosigkeit.",
                    highlights = listOf("Sieben Gesetze bei Paulus", "nomos bedeutet allgemein Anweisung")
                ),
                Section(
                    title = "Was bedeutet 'Unter dem Gesetz' (Hypo Nomon)?",
                    content = "Wer unter (hypo) dem Gesetz steht, steht unter dessen Verdammnis, Verurteilung und Strafmacht (dem Gesetz der Sünde und des Todes). Ein unschuldiger Bürger, der die verkehrsordnungsgemäßen Gesetze seines Landes hält, steht nicht 'unter' dem Gesetz im Sinne von polizeilicher Festnahme, sondern er lebt in Freiheit. Durch Jeschua sind wir frei von der Strafgebühr der Sünde (Tod), um nun im Geist das Gesetz Gottes mit Freude zu leben.",
                    highlights = listOf("hypo nomon = unter der Verdammnis des Gesetzes", "Gnade befähigt zum Gehorsam")
                ),
                Section(
                    title = "Gnade und Tora im Alten Testament",
                    content = "Gnade ist kein neues Konzept des NT. Noah fand Gnade in Gottes Augen vor dem Gesetz (1. Mose 6:8), und Mose fand ebenfalls absolute Gnade (2. Mose 33:12). Gottes Gesetz am Sinai war nie ein Mittel, sich Errettung durch Werke zu verdienen, sondern ein Geschenk der Unterweisung für ein erlöstes Volk."
                )
            )
        ),
        ThemaItem(
            id = "titus_1_15_tim",
            title = "Titus 1:15 & 1. Tim 4: Reine Speisen?",
            shortTitle = "Reinheit & Speisen",
            category = "Speisegebote",
            shortDescription = "Heilt 'den Reinen ist alles rein' alle Verbote auf? Wissenschaftliche und biblische Fakten.",
            durationMinutes = 20,
            scriptureRefs = "Titus 1:13-15, 1. Tim 4:1-5, 3. Mose 11",
            iconType = "food",
            objection = "Paulus klärt in Titus 1:15 'Den Reinen ist alles rein'. Und in 1. Timotheus 4 verurteilt er diejenigen, die verbieten, Speisen zu konsumieren, die Gott geschaffen hat. Also sind Speisegesetze vorbei.",
            sections = listOf(
                Section(
                    title = "Der wahre Kontext in Titus 1",
                    content = "Lies Titus 1:14 genau: Paulus warnt vor 'jüdischen Fabeln und Geboten von Menschen, die sich von der Wahrheit abwenden.' Es geht um rabbinische Zusatzgebote der Reinheit (Halacha), nicht um Gottes Gesetz! In 3. Mose 15 stand z.B., wie rituell unreine Tage geregelt werden. Rabbiner verschärften dies auf absurde Weise. Paulus kritisiert ungesunde, heuchlerische Reinheitsrituale, die Gottes Wort überschatteten.",
                    highlights = listOf("Warnung vor Geboten von Menschen", "Wahre Reinheit ist eine Herzenssache")
                ),
                Section(
                    title = "Geheiligt durch Gottes Wort (1. Timotheus 4)",
                    content = "In 1. Timotheus 4:5 schreibt Paulus, dass Speisen durch 'Gottes Wort' geheiligt (abgesondert) werden. Welches Wort Gottes sondert Nahrung aus? Es ist 3. Mose 11! Gott hat unreine Tiere biologisch gar nicht als 'Speise' (Nahrung) geschaffen. Schweine, Katzen oder Hummer dienen der Ökobilanz als Müllschlucker. Paulus verurteilt hier heidnische gnostische Asketen, die den Konsum von *erlaubtem* Fleisch und die Ehe grundsätzlich verboten.",
                    highlights = listOf("Geheiligt durch Gottes Wort = 3. Mose 11", "Müllschlucker-Funktion unreiner Tiere")
                ),
                Section(
                    title = "Physiologische Fakten: Toxizität und Verträglichkeit",
                    content = "Der Schöpfer will biologisch das Beste für uns. Medizinische Tests zeigen erstaunliche Zahlen zur Verträglichkeit und Toxizität von Säugetieren im Vergleich: Das Schwein hat eine biologische Verträglichkeit von nur 54%, die Katze 53% und der Hase 49%. Biblisch reine Säugetiere (Wiederkäuer mit gespaltenen Klauen) haben hingegen eine hervorragende Verträglichkeit von 82% bis 94%! Gott trennt Nahrung von Nicht-Nahrung zum Schutz unseres Körpers."
                )
            )
        ),
        ThemaItem(
            id = "apg_15_21_joch",
            title = "Apostelgeschichte 15 & 21 - Das Joch",
            shortTitle = "Konzil & Tora",
            category = "Hebräerbrief",
            shortDescription = "Warum legten die Apostel den Heiden nur vier Regeln auf? Die Wochenstruktur von Apg 15:21 gelöst.",
            durationMinutes = 22,
            scriptureRefs = "Apg 15:19-21, Apg 21:20-26",
            iconType = "law",
            objection = "Das Apostelkonzil hat entschieden, dass Heiden nur vier Gebote halten müssen (keine Unzucht, kein Blut, kein Ersticktes, kein Götzenopfer). Die restlichen Tora-Gebote gelten für sie nicht.",
            sections = listOf(
                Section(
                    title = "Der Retter-Irrtum: Beschneidungspharisäer in Vers 1",
                    content = "Die Grunddebatte in Apg 15:1 startete durch Irrlehrer: 'Wenn ihr euch nicht beschneiden lasst... könnt ihr nicht gerettet werden.' Sie machten die Konversion und Tora-Befolgung zur *Bedingung* zur Erlösung. Petrus wehrte dies ab: Die Erlösung geschieht rein durch die unverdiente Gnade des Messias (Apg 15:11).",
                    highlights = listOf("Errettung rein durch unverdiente Gnade", "Das unerträgliche Joch war Werksgerechtigkeit")
                ),
                Section(
                    title = "Apg 15:21 - Warum nur diese vier Regeln als Start?",
                    content = "Die vier Verbote (Meidung von Götzenopfer, Unzucht, Ersticktem und Blut) stammen direkt aus 3. Mose 17-18, wo geregelt wird, was ein 'Fremdling' in der israelitischen Tischgemeinschaft meiden MUSS, um nicht ausgerottet zu werden. Diese Regeln waren die Mindestanforderung, damit hebräische und heidnische Gläubige überhaupt gemeinsam essen konnten. Die Begründung folgt in Vers 21: 'Denn Mose hat von alten Zeiten her in jeder Stadt solche, die ihn verkündigen, da er in den Synagogen an jedem Sabbat vorgelesen wird.' Die Heiden sollten jeden Sabbat die restliche Tora lernen!",
                    highlights = listOf("Apg 15:21 erklärt den Jüngerschaftsplan", "Sabbatgottesdienst als stetiges Lernzentrum")
                ),
                Section(
                    title = "Paulus' Tora-Treue in Apg 21 bewiesen",
                    content = "In Apg 21:21 werfen Kritiker Paulus vor, er lehre Abfall von Mose. Um der Welt zu beweisen, dass dieses Gerücht eine reine Lüge ist, zahlt Paulus die Kosten für jüdische Naziräeropfer im Tempel und reinigt sich rituell. Jakobus sagt zu ihm (Apg 21:24): 'So werden alle erkennen... dass du ordentlich wandelst und das Gesetz hältst.' Paulus lebte und lehrte zeitlebens Tora-Treue."
                )
            )
        ),
        ThemaItem(
            id = "sabbat_bundeszeichen",
            title = "Der Sabbat - Ewiges Bundeszeichen oder veraltet?",
            shortTitle = "Sabbat & Bundeszeichen",
            category = "Grundlagen",
            shortDescription = "Warum der Sabbat ein zeitloses Geschenk Gottes an ALLES Fleisch ist. Jeschua, Schöpfung und Konstantin.",
            durationMinutes = 18,
            scriptureRefs = "Hes 20:12, Mk 2:27, Jes 58:13",
            iconType = "shadow",
            objection = "Der Sabbat ist nur für das jüdische Volk bestimmt und wurde am Sinai begründet. Christen feiern heute den Sonntag als den 'Herrentag' in Gedenken an die Auferstehung des Messias.",
            sections = listOf(
                Section(
                    title = "Geheiligt in der Schöpfung – lange vor Sinai",
                    content = "Der Sabbat wurde nicht erst am Berg Sinai eingeführt, sondern bereits in 1. Mose 2:1-3 von Gott selbst eingesetzt. Gott ruhte am siebten Tag, segnete ihn und sonderte ihn ab (weihte ihn). Zu diesem Zeitpunkt gab es weder ein Volk Israel noch 'Juden', sondern nur den ersten Menschen Adam als Vertreter der gesamten Menschheit. Der Messias bestätigt diese universelle Schöpfungsordnung in Markus 2:27: 'Der Sabbat ist um des Menschen willen geschaffen worden und nicht der Mensch um des Sabbats willen.' Er wurde für den Menschen (griech. anthropos = Menschheit) gemacht, nicht exklusiv für eine Ethnie.",
                    highlights = listOf("Schöpfungsordnung der Genesis", "anthropos = Für die gesamte Menschheit")
                ),
                Section(
                    title = "Ein unantastbares Zeichen ewiger Geltung",
                    content = "In Exodus 31:16-17 bezeichnet Gott den Sabbat als 'ewiges Bundeszeichen' (le-olam) zwischen ihm und den Kindern Israels. In Hesekiel 20:12 betont er: 'Ich gab ihnen auch meine Sabbate, dass sie ein Zeichen seien zwischen mir und ihnen...' Als eingepfropfte Fremdlinge in den Ölbaum Israels (Römer 11) teilen wir dieses ewige Bundeszeichen und ehren Gott dadurch als Schöpfer des Himmels und der Erde.",
                    highlights = listOf("le-olam = Ewig / unaufhörlich", "Schutzzeichen vor weltlichem Burnout")
                ),
                Section(
                    title = "Wie es historisch zur Verschiebung auf den Sonntag kam",
                    content = "Weder Jesus noch seine Jünger haben je an einem anderen Tag als dem Sabbat geruht oder gelehrt. Erst unter dem römischen Kaiser Konstantin dem Großen änderte sich dies politisch: Am 7. März 321 n. Chr. erließ er das historische staatliche Sonntagsdekret: 'Am ehrwürdigen Tag der Sonne sollen alle Richter und Stadtbewohner ruhen.' Später untermauerte die Kirche dies im Konzil von Laodizäa (Kanon 29, ca. 364 n. Chr.) unter Androhung des Kirchenbanns für jene, die samstags ruhten. Die Verschiebung beruht also auf kaiserlich-kirchlicher Machtpolitik, nicht auf der Heiligen Schrift.",
                    highlights = listOf("Kaiser Konstantin Dekret: 7. März 321", "Konzil von Laodizäa Kanon 29")
                )
            )
        ),
        ThemaItem(
            id = "galater_fluch",
            title = "Galaterbrief - Was ist der Fluch des Gesetzes?",
            shortTitle = "Galater & der Fluch",
            category = "Paulus & die Tora",
            shortDescription = "Eine exegetische Analyse von Galater 3:10-13. Was bedeutet 'ergon nomou'?",
            durationMinutes = 20,
            scriptureRefs = "Gal 3:10-13, Gal 5:1-4",
            iconType = "law",
            objection = "Paulus schreibt im Galaterbrief: 'Denn alle, die aus Gesetzeswerken sind, stehen unter dem Fluch.' Daraus folgt, dass jeder, der die Tora (Sabbat, Speisegebote) heute halten will, verflucht ist.",
            sections = listOf(
                Section(
                    title = "Die historische Redewendung: Gesetzeswerke (Ergon Nomou)",
                    content = "Um Paulus' Brief an die Galater zu begreifen, müssen wir den archäologischen Fund der Qumran-Rollen heranziehen. Dort fand man das hebräische Äquivalent 'Ma'ase haTorah' (Gesetzeswerke) im Brief 4QMMT. Es war ein technischer Fachbegriff einer bestimmten sektenartigen Glaubensgruppe von Pharisäern, die lehrten, dass man durch exakte Einhaltung zeremonieller Sonderregeln (wie rituelle Reinheit und sofortige Beschneidung) gerecht gesprochen wird. Paulus bekämpft vehement diese falsche Überzeugung: Niemand wird durch diese Werke vor Gott gerechtfertigt, sondern ausschließlich durch Glauben.",
                    highlights = listOf("ergon nomou / Ma'ase haTorah", "Beleg durch jüdische Qumran-Texte (4QMMT)")
                ),
                Section(
                    title = "Gekauft vom Fluch des Gesetzes",
                    content = "In Galater 3:13 schreibt Paulus: 'Christus hat uns losgekauft von dem Fluch des Gesetzes, indem er ein Fluch für uns geworden ist.' Der Satz sagt NICHT, das Gesetz sei ein Fluch. Das Gesetz ist heilig, gerecht und gut (Römer 7:12). Doch das Gesetz trägt in sich einen *Fluch* für Übertreter (Sünder): den Tod (5. Mose 27:26, Römer 6:23). Christus starb an unserer statt, um den fälligen Fluch (die Todesstrafe) für unsere Tora-Brüche wegzunehmen! Er erlöste uns vom Fluch der Todesstrafe, nicht vom treuen Gehorsam gegenüber Gottes Willen.",
                    highlights = listOf("Der Fluch des Gesetzes ist die Todesstrafe", "Das Gesetz an sich ist gut und heilig")
                ),
                Section(
                    title = "Ist Gehorsam nun geistlose Sklaverei?",
                    content = "Paulus warnt in Galater 5 vor dem 'Joch der Knechtschaft'. Dieses Joch ist der untaugliche Versuch der Selbstjustiz – den Bund des Sinai als reines Werksystem zu missbrauchen, um Errettung zu erzwingen. Wenn wir jedoch die Tora freiwillig im heiligen Geist aus inniger Liebe zum Retter halten, wandeln wir im Geist des wahren, lebendigen Glaubens. Gehorsam ist keine Sklaverei, sondern das glückliche Leben eines erlösten Gotteskindes."
                )
            )
        ),
        ThemaItem(
            id = "apg_10_petrus_vision",
            title = "Apostelgeschichte 10 - Petrus' Vision der Tiere",
            shortTitle = "Apostelgeschichte 10",
            category = "Speisegebote",
            shortDescription = "Schlachtete und aß Petrus wirklich Unreines? Wie das Neue Testament seine eigene Symbolik erklärt.",
            durationMinutes = 15,
            scriptureRefs = "Apg 10:9-28, Apg 11:1-18",
            iconType = "food",
            objection = "Petrus erhält ein großes Leinentuch voll unreiner Tiere aus dem Himmel und Gott gebietet ihm: 'Schlachte und iss!' Das beweist unmissverständlich, dass nun alle Speisen reingewaschen sind.",
            sections = listOf(
                Section(
                    title = "Petrus' kompromisslose Tora-Treue",
                    content = "Als die Stimme ruft 'Schlachte und iss!', antwortet Petrus augenblicklich: 'Keineswegs, Herr! Denn ich habe noch nie etwas Gemeines oder Unreines gegessen.' (Apg 10:14). Das Ereignis fand ca. 10 Jahre nach der Kreuzigung und Himmelfahrt Jesu statt! Wenn der Messias die Speisegebote je während seines irdischen Wirkens aufgelöst hätte, wie many Ausleger behaupten, hätte Petrus das gewusst und niemals mit solch tora-treuem Entsetzen reagiert.",
                    highlights = listOf("Ereignis fand ca. 10 Jahre nach Jesu Kreuzigung statt", "Beweis für kontinuierliches Halten der Tora nach Pfingsten")
                ),
                Section(
                    title = "Die Auslegung der Vision durch Petrus selbst",
                    content = "Viele hören beim Lesen nach Vers 16 auf. Doch die Bibel lässt uns bei der Symbolik nicht im Dunkeln stehen. In Vers 28 löst der Apostel selbst das Rätsel unmissverständlich auf: 'Ihr wisst, wie unerlaubt es für einen jüdischen Mann ist, sich einem Fremdling anzuschließen ... Aber Gott hat mir gezeigt, keinen MENSCHEN gemein oder unrein zu nennen.' Das Tuch mit den wilden Tieren stand symbolisch für die Heidenvölker, die nach pharisäischem Menschengebot (Halacha) als unrein galten. Die Vision war kein Speisekammer-Gesetz, sondern ein Befehl zur Völker-Jüngerschaft!",
                    highlights = listOf("Heidenvölker symbolisiert durch unreine Tiere", "Gott reinigt Menschen, keine Müllschlucker-Tiere")
                ),
                Section(
                    title = "Die Jüngerschaftsfolge an Cornelius",
                    content = "Am Ende der Vision kommt der Heide Cornelius zum Glauben. Der heilige Geist fällt auf ihn und sein Haus. Das Neue Testament bestätigt das eigentliche Ziel des Wunders: Gott hat den Heiden den Weg der Umkehr zum ewigen Leben geschenkt (Apg 11:18). Die Speisegebote aus Levitikus 11 bleiben vollkommen biologisch und geistlich in Kraft, um die Heiligkeit des Tempels unseres Körpers zu bewahren."
                )
            )
        ),
        ThemaItem(
            id = "roemer_14",
            title = "Römer 14 - Fastentage, Fleisch & Heiden-Probleme",
            shortTitle = "Römer 14 & Fasten",
            category = "Paulus & die Tora",
            shortDescription = "Warum Römer 14 von Streitigkeiten über Fastenpraktiken handelt und den Sabbat keineswegs auflöst.",
            durationMinutes = 18,
            scriptureRefs = "Röm 14:1-6, Röm 14:14-20",
            iconType = "food",
            objection = "In Römer 14:5 schreibt Paulus: 'Dieser zieht einen Tag vor den anderen, jener aber hält jeden Tag gleich; ein jeder sei in seinem Sinn völlig gewiss.' Das beweist, dass es völlig egal ist, ob wir den Sabbat oder den Sonntag feiern.",
            sections = listOf(
                Section(
                    title = "Der historische Kontext: Asketen und Fastentage im kaiserlichen Rom",
                    content = "Die Streitigkeiten in Rom handelten nicht vom vierten Gebot (Sabbat) – das Wort Sabbat taucht im gesamten Römerbrief nicht ein einziges Mal auf! Paulus spricht zu Beginn von zwei Lagern: Jene, die alles essen, und jenen, die 'schwach im Glauben' sind und nur Gemüse konsumieren (Röm 14:2). Warum? Im heidnischen Rom war es fast unmöglich, koscheres Fleisch im öffentlichen Handel zu erwerben, das nicht zuvor Götzen geweiht war. Einige gläubige Ex-Pharisäer oder jüdische Asketen wichen daher auf strikten Vegetarismus aus, während andere unbedenklich aßen. Die Debatte war eine Gewissensfrage zu heidnischen Marktplätzen.",
                    highlights = listOf("Fastentradition im Judentum (Montag & Donnerstag)", "Vegetarismus aus Furcht vor Götzenfleisch")
                ),
                Section(
                    title = "Welcher Tag wird hier eingeschätzt?",
                    content = "Kritiker missbrauchen Vers 5, um den Ruhetag als obsolet zu deklarieren. Im antiken Rom gab es jedoch heftige Streitigkeiten darüber, an welchen freiwilligen Tagen der Woche Christen fasten sollten: Einige taten es am jüdischen Fastentag, andere an speziellen Wochentagen. Paulus klärt diplomatisch auf: Wer fastet, tut es dem Herrn zu Ehren – wer nicht fastet, ebenfalls. Es ist unvorstellbar, dass Paulus hier das göttliche Dekret des Sabbats, das in Stein gemeißelt wurde, als 'Meinungsstreit' (Röm 14:1) herabstuft.",
                    highlights = listOf("Freiwillige Fastentage", "Keine Bezugnahme auf den biblischen Sabbat")
                ),
                Section(
                    title = "Das griechische Wort 'Koinos' in Vers 14",
                    content = "Erneut schreibt Paulus: 'Ich weiß... dass nichts an sich unrein (koinos) ist.' Wie wir bereits gesehen haben, bedeutet Koinos 'rituell entweiht oder rituell gemein' (z.B. durch Berührung mit Ungeweihtem). Es bedeutet nicht 'Akathartos' (von Natur aus abscheulich / unreine Schöpfung). Paulus lehrt, dass reines Fleisch nicht rituell verunreinigt (koinos) wird, bloß weil es im römischen Tempelgebiet gelagert wurde. Wir dürfen reine Tiere mit gutem Gewissen verzehren.",
                    highlights = listOf("koinos = rituell gemein", "Keine Abschaffung der Schöpfungsordnung aus Lev 11")
                )
            )
        ),
        ThemaItem(
            id = "feste_des_herrn",
            title = "Die Feste des HERRN - Prophetischer Heilsfahrplan",
            shortTitle = "Die Feste des Herrn",
            category = "Grundlagen",
            shortDescription = "Warum Sabbat, Passah und Sukkot keine veralteten Traditionen sind, sondern Gottes ewige Verabredungen.",
            durationMinutes = 22,
            scriptureRefs = "3. Mose 23:1-4, Sach 14:16-19, Kol 2:16-17",
            iconType = "book",
            objection = "Die Feste (Passah, Wochenfest, Laubhüttenfest) waren jüdische Schatten, die am Kreuz ihr Ende fanden. Kolosser 2:16 sagt, wir sollen uns wegen Festen nicht richten lassen.",
            sections = listOf(
                Section(
                    title = "Die Termine des Königs aller Könige: Mo'edim",
                    content = "In 3. Mose 23:2 bezeichnet Gott die Feste unmissverständlich als 'Meine festgesetzten Zeiten' (hebr. Mo'edim = Verabredungen oder Treffpunkte) und als 'heilige Versammlungen'. Sie gehören nicht exklusiv den Juden, sondern sie sind Gottes Heilsagenda mit der gesamten Menschheit. Der wöchentliche Sabbat ist der allererste listed Mo'ed. Wenn wir die Feste feiern, gehen wir zu einem festen, himmlischen Verabredungstermin mit unserem Schöpfer.",
                    highlights = listOf("Mo'edim = Gesetzte Termine / Verabredungen", "Es sind Gottes Feste, nicht nur ethnische Bräuche")
                ),
                Section(
                    title = "Der perfekte prophetische Zeitstrahl des Messias",
                    content = "Die Feste des Herrn zeichnen den Heilsplan Jesu haargenau ab. Die Frühlingsfeste wurden bei Jesu erstem Kommen auf den Tag genau erfüllt: Das Passahfest zeigt Seinen Tod vor dem Altar, das Fest der ungesäuerten Brote Seine Sündlosigkeit im Grab, die Erstlingsgabe Seine Auferstehung und das Wochenfest (Pfingsten) die Ausgießung des Heiligen Geistes. Die Herbstfeste (Posaunenschall, Sühnetag, Laubhüttenfest) kündigen Seine Wiederkunft, das letzte Gericht und des Wohnen Gottes inmitten Seines Volkes an.",
                    highlights = listOf("Frühlingsfeste zeigten erstes Kommen", "Herbstfeste kündigen zweites Kommen an")
                ),
                Section(
                    title = "Sukkot-Pflicht für alle Nationen in Sacharja 14",
                    content = "In der messianischen Zukunft, wenn der Messias auf der Erde in Gerechtigkeit regiert, müssen laut Sacharja 14:16-18 alle Nationen – auch Heiden, Deutsche, Römer – Jahr für Jahr nach Jerusalem pilgern, um das Laubhüttenfest (Sukkot) zu feiern. Wer dies verweigert, wird mit Dürre ohne Regen bestraft. Wenn Gottes Feste in der Zukunft für die ganze Welt universell gelten, warum sollten wir sie heute aus Liebe vernachlässigen?",
                    highlights = listOf("Sacharja 14:16-18", "Sukkot ist der zukünftige globale Feiertag")
                )
            )
        ),
        ThemaItem(
            id = "neuer_bund",
            title = "Der Neue Bund - Mit wem wurde er geschlossen?",
            shortTitle = "Der Neue Bund",
            category = "Israel & die Heiden",
            shortDescription = "Warum Hebräer 8:8 offenbart, dass der Neue Bund ausschließlich mit Israel und Juda vollzogen wurde.",
            durationMinutes = 18,
            scriptureRefs = "Jer 31:31-33, Heb 8:8-10, Eph 2:12-19",
            iconType = "priesthood",
            objection = "Der Neue Bund hat das jüdische Haus Israel abgelöst und durch die christliche Gemeinde (Kirche) ersetzt. Unter diesem Bund gelten die Gesetze Mose nicht mehr.",
            sections = listOf(
                Section(
                    title = "Das biblische Adressatenschreiben des Bundes",
                    content = "Wenn wir Hebräer 8:8 und Jeremia 31:31 lesen, erleben viele traditionelle Ausleger eine Erschütterung: 'Siehe, es kommen Tage... da werde ich mit dem Haus Israel und mit dem Haus Juda einen neuen Bund schließen!' Nirgends in der Bibel existiert ein 'Neuer Bund' mit Heidenvölkern, Europa oder einer losgelösten 'Heidenkirche'. Der Bundespartner ist und bleibt das ungeteilte Volk Israel. Heiden können nur Teil dieses Bundes werden, indem sie durch den Glauben an den Messias der jüdischen Bürgerschaft eingepfropft werden.",
                    highlights = listOf("Eph 2:12: Fremde der Bündnisse der Verheißung", "Bürgerrecht Israels durch das Blut Jeschuas")
                ),
                Section(
                    title = "Die Substanz des Bundes: Die Tora im Herzen",
                    content = "Der Neue Bund schafft Gottes Tora (Gottes Weisung) nicht ab. Ganz im Gegenteil, der Bundesinhalt definiert sich laut Hebräer 8:10 exakt so: 'Ich will meine Gesetze (Tora) in ihren Sinn geben und sie auch auf ihre Herzen schreiben.' Der Unterschied zum Alten Bund am Sinai ist nicht das Gesetz, sondern das Speichermedium! Am Sinai stand die Tora auf vergänglichem Stein (äußerlich); im Neuen Bund schreibt der Heilige Geist das Gesetz der Liebe direkt in unsere Herzen, um uns zum Gehorsam zu befähigen.",
                    highlights = listOf("Verzug von Steintafeln zu Herzentafeln", "Hesekiel 36:27: Wandeln in Meinen Rechten")
                ),
                Section(
                    title = "Das messianische Bürgerrecht in Epheser 2",
                    content = "Paulus erinnert Heidenchristen in Epheser 2:19, dass sie nun nicht länger Fremdlinge ohne Bürgerrecht sind, sondern 'Mitbürger der Heiligen und Hausgenossen Gottes'. Ein Mitbürger teilt das gleiche Grundgesetz, die gleichen Feste und den gleichen königlichen Lebensstil. Die Tora ist kein veraltetes Gesetzbuch für ein anderes Volk, sondern die harmonische Verfassung deines neuen, geistlichen Vaterlandes.",
                    highlights = listOf("Mitbürger der Heiligen im Haus Gottes", "Keine Spaltung der göttlichen Familie")
                )
            )
        )
    )

    val QUIZ_QUESTIONS = listOf(
        QuizQuestion(
            id = 1,
            question = "Welches griechische Wort wird in Matthäus 5:17 für 'erfüllen' genutzt und beschreibt das Füllen beziehungsweise die Veranschaulichung im vollen Maße?",
            options = listOf(
                "Das Verb 'Kataluo', welches das irreversible Auflösen, Dekonstruieren oder das Außerkraftsetzen einer gesetzlichen Weisung deklariert.",
                "Das Verb 'Pleroo', welches das Anfüllen, das Ausleben im vollen Ausmaß beziehungsweise das Demonstrieren der idealen Praxis meint.",
                "Das Verb 'Anaireo', welches das administrative Streichen, die physische Vernichtung beziehungsweise die Beseitigung einer Satzung bezeichnet.",
                "Das Nomen 'Nomos', welches im antiken Griechisch die allgemeine Gesetzgebung, das geschriebene Gesetz oder eine Verordnung benennt."
            ),
            correctIndex = 1,
            explanation = "Das Wort 'pleroo' bedeutet anfüllen, voll machen oder zur vollen Entfaltung bringen. Es ist das Gegenteil von 'kataluo' (auflösen, zerstören).",
            difficulty = "Einsteiger"
        ),
        QuizQuestion(
            id = 2,
            question = "Was ist der theologische und semantische Hauptunterschied zwischen den neutestamentlichen Begriffen 'koinos' und 'akathartos'?",
            options = listOf(
                "Es existiert im hellenistischen Griechisch kein eigenständiger Unterschied; beide Termini bezeichnen gleichwertig rituelle Sünden.",
                "Koinos betrifft die nachträgliche rituelle Verunreinigung (gemein machen); akathartos meint die von Natur aus unreinen Tiere laut Tora.",
                "Akathartos definiert die böse Gesinnung im menschlichen Herzen; koinos bezieht sich auf die physiologische Ungesundheit von Tieren.",
                "Koinos beschreibt die rituelle Ausgrenzung der Heidenvölker; akathartos regelt die Priesterweihen, welche im Tempel vollzogen wurden."
            ),
            correctIndex = 1,
            explanation = "Koinoo meint speiserechtlich unheilig/rituell gemein geworden (z.B. durch ungewaschene Hände). Akathartos bezieht sich auf die von Gott in der Tora verbotenen Tiere.",
            difficulty = "Mittel"
        ),
        QuizQuestion(
            id = 3,
            question = "Welches alttestamentliche Buch enthält die detaillierten Festzeiten (Mo'edim) wie Passah, Sabbat und Laubhüttenfest en detail?",
            options = listOf(
                "Das erste Buch Mose (Genesis), welches die vorsinaitischen Bundesschlüsse und Schöpfungsabfolgen systematisch dokumentiert.",
                "Das dritte Buch Mose (Levitikus, Kapitel 23), das all die heiligen Festzeiten des HERRN sowie die zyklische Sabbatruhe festlegt.",
                "Der Brief des Apostels Paulus an die Römer, welcher die jüdische Festpraxis für Heidenchristen im Detail kritisch einordnet.",
                "Das Johannesevangelium, welches die Festbräuche in einer rein gnostischen Metaphorik auf den Glauben der Christen überträgt."
            ),
            correctIndex = 1,
            explanation = "3. Mose 23 listet all die festgesetzten heiligen Zeiten Gottes (Mo'edim) inklusive des wöchentlichen Sabbats auf.",
            difficulty = "Einsteiger"
        ),
        QuizQuestion(
            id = 4,
            question = "Was bedeutet der im Hebräerbrief (Kapitel 7, Vers 12) verwendete Begriff 'metathesis' im originalen Wortlaut?",
            options = listOf(
                "Die restlose Aufhebung, die theologische Deaktivierung beziehungsweise die endgültige Abschaffung einer biblischen Urkunde.",
                "Die administrative Umplatzierung, der gesetzliche Transfer beziehungsweise die feierliche Relokation einer priesterlichen Ordnung.",
                "Das Inkrafttreten einer neuen Gnadenzeit, welche die mosaischen Vorschriften durch ein rein inneres Gewissengesetz ersetzt.",
                "Die feierliche Absolution im irdischen Tempeldienst, welche den Söhnen Aarons das Vorrecht der Sündopferung permanent entzieht."
            ),
            correctIndex = 1,
            explanation = "Metathesis leitet sich von metatithemi ab und bedeutet Umplatzierung oder Standortwechsel (wie Henochs Entrückung in Heb 11:5). Das Priestertum wechselte den Ort von der Erde in den Himmel.",
            difficulty = "Experte"
        ),
        QuizQuestion(
            id = 5,
            question = "In welcher alttestamentlichen Verheißung wird prophezeit, dass im kommenden Friedensreich alle Völker das Laubhüttenfest feiern müssen?",
            options = listOf(
                "Das Buch der Schöpfung (Genesis, Kapitel 1), welches die vorsinaitischen Ruhezeiten und Festzyklen der Nationen begründet.",
                "Der messianische Königspalm (Psalm 23), welcher das friedliche Wohnen im Hause Gottes und die ewige Sabbatruhe ideal abbildet.",
                "Das Buch des Propheten Sacharja (Kapitel 14, Verse 16 bis 18), welches die globale Pilgerpflicht zur Sukkoth-Feier ankündigt.",
                "Das Buch des Propheten Maleachi (Kapitel 3), welches die radikale Erneuerung des Opferkults und das Ende der Feste voraussagt."
            ),
            correctIndex = 2,
            explanation = "In Sacharja 14:16-17 steht geschrieben, dass alle Übriggebliebenen der Heidenvölker Jahr für Jahr nach Jerusalem aufziehen werden um das Laubhüttenfest zu feiern.",
            difficulty = "Experte"
        ),
        QuizQuestion(
            id = 6,
            question = "Wer wird in der exegetischen Auslegung von Römer 11 in den edlen Kulturölbaum Israels eingepfropft?",
            options = listOf(
                "Das physische Haus Israel als Bundespartner, dessen ungläubige Zweige durch das Konversionsritual neu verwurzelt werden.",
                "Die Heidenchristen aus den Nationen, die allein durch den Glauben dem edlen Kulturölbaum vollkommen eingepfropft wurden.",
                "Die Pharisäer der herodianischen Epoche, die aufgrund ihrer Gesetzeswerke die geistliche Herrschaft verloren hatten.",
                "Die heidnischen Völker Mesopotamiens, welche nach dem Exil eine separate, vom mosaischen Gesetz gelöste Gemeinde bildeten."
            ),
            correctIndex = 1,
            explanation = "Die gläubigen Heiden werden wie Zweige eines wilden Ölbaums in den edlen Ölbaum Israels eingepfropft und teilen dessen Verheißungen und Lebensstil.",
            difficulty = "Mittel"
        ),
        QuizQuestion(
            id = 7,
            question = "Was war die theologische Kerndebatte auf dem ersten Apostelkonzil in Apostelgeschichte Kapitel 15?",
            options = listOf(
                "Die fundamentale Frage, ob Heidenchristen aus den Nationen den wöchentlichen Bundessabbat rituell einhalten müssen.",
                "Die Kontroverse, ob Heiden sich einer pharisäischen Konvertiten-Beschneidung unterziehen müssen, um errettet zu werden.",
                "Der theologische Disput, ob die leibliche Auferstehung des Messias die Opferrituale im jüdischen Tempel sofort beendet.",
                "Die ethische Frage, ob der Konsum von reinem Speisefleisch auf paganen Marktplätzen die Errettung des Sünders gefährdet."
            ),
            correctIndex = 1,
            explanation = "In Apg 15:1 forderten Männer aus Judäa: 'Wenn ihr euch nicht beschneiden lasst, könnt ihr nicht gerettet werden.' Es ging um die Beschneidung als *Bedingung* zur Errettung (Gesetzlichkeit).",
            difficulty = "Mittel"
        ),
        QuizQuestion(
            id = 8,
            question = "Welcher grammatikalische Einwand existiert bezüglich des Begriffs 'Schatten' in Kolosser Kapitel 17?",
            options = listOf(
                "Das theologische Subjekt 'Sabbat', welches von antiken Schreibern willkürlich aus dem griechischen Text gelöscht wurde.",
                "Das restriktive Wort 'nur' (in 'nur ein Schatten'), welches im originalen griechischen Grundtext fehlt und zugefügt wurde.",
                "Die christologische Apposition 'Christus', welche als exklusive Erfüllung der Feste den eigentlichen Schatten auflöst.",
                "Der göttliche Eigenname 'Jahwe', welcher als Siegel der Sabbatheiligung die zeitliche Begrenzung der Tora deklariert."
            ),
            correctIndex = 1,
            explanation = "Das Wort 'nur' (fälschlich eingefügt als 'nur ein Schatten') existiert im altgriechischen Grundtext nicht. Ein Schatten ist ein prophetischer Pointer auf die Realität, keine unbedeutende Nebensache.",
            difficulty = "Experte"
        ),
        QuizQuestion(
            id = 9,
            question = "Wovor warnt Paulus in Titus 1:14-15 im Kontext der bekannten Formulierung 'den Reinen ist alles rein'?",
            options = listOf(
                "Vor den alttestamentlichen Speiseverboten aus Levitikus, welche eine unleidliche Barriere für Heidenchristen darstellen.",
                "Vor außerbiblischen jüdischen Fabeln und von Menschen erfundenen Reinheitsvorschriften, die Gottes Wort überschatten.",
                "Vor der physischen Arbeit am Sabbat, welche in paganen Kreisen als absolute Entweihung des christlichen Glaubens galt.",
                "Vor dem Verzehr von rohem Gemüse, das von aszetischen Strömungen als einziges fleischloses Nahrungsmittel erlaubt war."
            ),
            correctIndex = 1,
            explanation = "Titus 1:14 belegt ganz unmissverständlich, dass Paulus vor 'jüdischen Fabeln' und unbiblischen 'Geboten von Menschen' warnt, die falsche äußere rituell verschärfte Reinheitsrituale auferlegten.",
            difficulty = "Mittel"
        ),
        QuizQuestion(
            id = 10,
            question = "Wie hoch ist laut nahrungsbiologischen Tests die Verträglichkeit reiner Tiere im Vergleich zu Schweinefleisch?",
            options = listOf(
                "Eine mangelhafte Verträglichkeit, die im physiologischen Bereich bei nur etwa fünfundvierzig bis fünfzig Prozent einzuordnen ist.",
                "Eine hervorragende Verträglichkeit von zweiundachtzig bis vierundneunzig Prozent aufgrund des spezifischen Verdauungsapparates.",
                "Eine exakt zehn Prozent geringere Verträglichkeit, die sich durch die Zusammensetzung der tierischen Fettsäuren begründet.",
                "Eine vollkommen identische Verträglichkeit von vierundfünfzig Prozent, wodurch jedes Fleisch physiologisch gleichwertig ist."
            ),
            correctIndex = 1,
            explanation = "Biblisch reine Säugetiere (Wiederkäuer mit gespaltenen Klauen) besitzen eine Verträglichkeit von 82% bis 94%. Unreine Tiere wie das Schweinefleisch (54%), die Katze (53%) oder der Hase (49%) weisen signifikant geringere Verträglichkeiten auf.",
            difficulty = "Mittel"
        ),
        QuizQuestion(
            id = 11,
            question = "Was war laut Apostelgeschichte 15:21 der Zweck der vorläufigen vier Mindestverbote für Heiden?",
            options = listOf(
                "Das theologische Ziel, den nichtjüdischen Konvertiten jegliche Verantwortung bezüglich der restlichen Gebote zu entziehen.",
                "Das Schaffen einer rituell reinen Tischgemeinschaft, um stufenweise an jedem Sabbat die Tora in den Synagogen zu erlernen.",
                "Die Reduzierung der Steuer- und Tempelpflichten, um die Missionierung in der griechisch-römischen Antike zu beschleunigen.",
                "Die strikte Verordnung eines vegetarischen Lebensstils, um Kontaminationen mit dem heidnischen Opferdienst zu meiden."
            ),
            correctIndex = 1,
            explanation = "Apg 15:19-20 legte die Einstiegsschwelle zur Tischgemeinschaft fest. Vers 21 begründet es: Sie hatten jeden Sabbat in den Synagogen Zugang zur kontinuierlichen Lesung des Mose (Tora-Jüngerschaft).",
            difficulty = "Experte"
        ),
        QuizQuestion(
            id = 12,
            question = "Wie bewies der Apostel Paulus in Apostelgeschichte Kapitel 21 öffentlich seine Tora-Treue vor dem jüdischen Volk?",
            options = listOf(
                "Indem er Jerusalem unter Protest verließ, um die absolute Aufgeteiltheit der Tempelordnung in Heidenländern zu predigen.",
                "Indem er sich rituell reinigte und die vollen Opferkosten für vier jüdische Naziräer übernahm, um Gerüchte zu widerlegen.",
                "Indem er das pharisäische Händewaschen verurteilte und den Heidenchristen jeden Kontakt zum irdischen Tempel untersagte.",
                "Indem er lehrte, dass der gesamte irdische Tempelkult ab sofort als schwere Vergewaltigung der Gnade anzusehen ist."
            ),
            correctIndex = 1,
            explanation = "Jakobus veranlasste Paulus dazu, um zu demonstrieren, 'dass an den Gerüchten nichts ist, sondern dass du ordentlich wandelst und das Gesetz hältst.' Paulus zahlte Naziräeropfer im Tempel.",
            difficulty = "Experte"
        ),
        QuizQuestion(
            id = 13,
            question = "Welcher griechische Begriff wird verwendet, um eine eigentlich reine Speise zu beschreiben, die rituell verunreinigt wurde?",
            options = listOf(
                "Der Begriff 'Akathartos', welcher die von Natur aus unreine Schöpfung (wie das Schwein oder Raubvögel) im Levitikus bezeichnet.",
                "Der Begriff 'Koinos', der eigentlich reine Speisen betrifft, die rituell durch Kontakt mit Ungewaschenem gemein wurden.",
                "Der Begriff 'Pleroo', das im Neuen Testament das Füllen beziehungsweise das Ausgießen der geistlichen Heilsgnade deklariert.",
                "Der Ausdruck 'Hypo nomon', welcher den Gläubigen unter der drakonischen Verurteilungs- und Strafgewalt des Gesetzes darstellt."
            ),
            correctIndex = 1,
            explanation = "Koinos is das altgriechische Wort für unheilig oder rituell gemein/entweiht gewordene Gegenstände, während Akathartos von Natur aus unreines Fleisch beschreibt.",
            difficulty = "Mittel"
        ),
        QuizQuestion(
            id = 14,
            question = "An welchem konkreten historischen Datum erließ Kaiser Konstantin das berühmte erste Dekret zur Sonntagsruhe?",
            options = listOf(
                "Am Weihnachtsfest unter der Herrschaft von Kaiser Karl dem Großen, welches am fünfundzwanzigsten Dezember achthundert stattfand.",
                "Am siebten März dreihunderteinundzwanzig nach Christus durch das allgemeine imperiale Ruhetagsedikt des Kaisers Konstantin.",
                "Am ersten Januar einhundert nach Christus, als direkte Folge der Zerstörung des jüdischen Urtempels durch General Titus.",
                "Am vierzehnten Nissan einhundertfünfunddreißig nach Christus, unmittelbar nach der Niederschlagung des Bar-Kochba-Aufstandes."
            ),
            correctIndex = 1,
            explanation = "Am 7. März 321 n. Chr. verordnete Konstantin den 'ehrwürdigen Tag der Sonne' als allgemeinen Ruhetag, um die Kirche endgültig vom Sabbat jüdischen Ursprungs zu distanzieren.",
            difficulty = "Experte"
        ),
        QuizQuestion(
            id = 15,
            question = "Aus welchem Hauptgrund feierten jüdische Erstgläubige nach Jesu Tod historisch keine Tieropfer mehr am Tempel?",
            options = listOf(
                "Weil der Messias ihnen beim letzten Abendmahl die Ausübung jeglicher Opferhandlungen im Tempel verbot.",
                "Weil der Tempel in Jerusalem im Jahre siebzig nach Christus von den Römern komplett zerstört wurde und die Opferstätte erlosch.",
                "Weil die finanzielle Belastung für den Erwerb von fehlerfreien Opferlamm-Tieren in der nachapostolischen Zeit zu groß war.",
                "Weil griechische Gnostiker den Opferritus als unrein verdammten und stattdessen die rein moralische Andacht einführten."
            ),
            correctIndex = 1,
            explanation = "Historisch gab es rein theologische Ersatzbegründungen erst Jahrhunderte später. Die ersten Gläubigen dienten treu am Tempel (Apg 2:46, 3:1), bis dieser im Jahre 70 n. Chr. verbrannt wurde.",
            difficulty = "Mittel"
        ),
        QuizQuestion(
            id = 16,
            question = "Wie ist der hebräische Begriff 'Tora' (תּוֹרָה) aus biblischer und linguistischer Sicht korrekt zu übersetzen?",
            options = listOf(
                "Unbarmherzige Knechtschaft, drakonisches Gesetzeswerk oder ein sklavenhaftes Joch, welches Verdammnis ankündigt.",
                "Liebevolle Wegweisung, väterliche Lehre oder weise Unterweisung, die den Gläubigen heilsam auf dem Lebensweg führt.",
                "Das gesamte Alte Testament, welches im Kontrast zum Neuen Testament keine gnadenreiche Heilsbotschaft mehr vermittelt.",
                "Die mosaische Gesetzestafel, welche ausschließlich die zehn Gebote enthält, während Opfergesetze verworfen wurden."
            ),
            correctIndex = 1,
            explanation = "Tora leitet sich von 'yarah' ab und bedeutet 'ein Ziel anvisieren, Wegweisung geben, unterweisen'. Es ist fälschlicherweise im Griechischen als 'nomos' und im Deutschen als 'Gesetz' übersetzt worden, was einen legalistischen Beigeschmack erzeugt.",
            difficulty = "Einsteiger"
        ),
        QuizQuestion(
            id = 17,
            question = "Welches vorrangige ökologische Amt zugesellt Gott den unreinen Tierarten wie Schweinen oder Krustentieren?",
            options = listOf(
                "Sie waren von vornherein als primäre nahrungsphysikalische Fleischquelle für die heidnischen Völker außerhalb Israels gedacht.",
                "Sie agieren als biologische Müllschlucker und filternde Katalysatoren zur Reinigung giftiger Schadstoffe im globalen Ökosystem.",
                "Sie besitzen im biologischen Tierkreislauf keinerlei Funktion und dienten rein als symbolisches Abgrenzungsgebot für Hebräer.",
                "Sie wurden zu dekorativen Zwecken erschaffen, um die bunte Artenvielfalt der göttlichen Naturordnung bildhaft zu spiegeln."
            ),
            correctIndex = 1,
            explanation = "Unreine Tiere reinigen Böden und Gewässer von Aas, Detritus und toxischen Abfällen. Schweine fressen fast alles und verdauen sehr schnell ohne Schweißdrüsen; Krebse und Muscheln filtern Schadstoffe aus dem Wasser. Sie sind 'Natur-Filter' und nicht für den menschlichen Teller bestimmt.",
            difficulty = "Mittel"
        ),
        QuizQuestion(
            id = 18,
            question = "In welchem antiken jüdischen Fund wird die hebräische Formulierung für 'Gesetzeswerke' (Ma'ase haTorah) belegt?",
            options = listOf(
                "Die monumentale Tempelrolle, welche die Konstruktionsmaße und Festopferriten des Tempels detailliert prophezeit.",
                "Der Brief 4QMMT aus den Höhlen von Qumran, welcher die dogmatischen Sonderregeln einer pharisäischen Sektengruppe belegt.",
                "Das apokryphe Buch Henoch, welches die Konstellationen der Himmelskörper und historische Endzeitgerichte beschreibt.",
                "Die Makkabäerbücher, welche den militärischen Aufstand gegen Seleukiden und die Einweihung des Tempels dokumentieren."
            ),
            correctIndex = 1,
            explanation = "Der Fund des Briefes 4QMMT in Qumran bewies, dass 'Gesetzeswerke' ein technischer Begriff für konfessionelle Zusatzvorschriften was, um sich als gerechte Elite abzusondern. Paulus wehrt diese rituelle Ausgrenzung in Galater und Römer ab.",
            difficulty = "Experte"
        ),
        QuizQuestion(
            id = 19,
            question = "Wann wurde das Ruhesiegel des Sabbats laut der biblischen Berichterstattung geheiligt und instituiert?",
            options = listOf(
                "Am Sinai während der spektakulären Gesetzgebung unter Blitz und Donner, als nationales Bundesrecht des Volkes Israel.",
                "Am siebten Tag der Schöpfungswoche (Genesis 2:1-3) durch Gottes eigene Ruhe und Absonderung, lange vor jeglicher jüdischer Ethnie.",
                "Während des babylonischen Exils durch Proklamation des Jesaja, um der heimatlosen Diaspora eine eigene Identität zu verleihen.",
                "In der Bergpredigt durch den Messias Jeschua, welcher den wöchentlichen Ruhetag auflöste und rein geistig reformierte."
            ),
            correctIndex = 1,
            explanation = "Gott segnete und heiligte den siebten Tag direkt am Ende der Schöpfungswoche. Daher ist der Sabbat kein 'jüdisches' Sonderschulungsgesetz, sondern eine universelle Schöpfungsordnung für jeden Menschen (anthropos, Mk 2:27).",
            difficulty = "Einsteiger"
        ),
        QuizQuestion(
            id = 20,
            question = "Was bedeutet paulonisch die Aussage, wir seien vom 'Fluch des Gesetzes' (Galater 3:13) freigekauft?",
            options = listOf(
                "Dass die heilige Tora Jesu Christi ein böser, drakonischer Fluch war, welcher am Holz endgültig vernichtet werden musste.",
                "Dass wir von der im Gesetz festgeschriebenen Todesstrafe für unsere Sünden durch das stellvertretende Opfer erlöst wurden.",
                "Dass dem neubündischen Gläubigen ab sofort die absolute Ungültigkeit aller Gebote verheißen ist und Gehorsam sündhaft sei.",
                "Dass die Erlösung am Kreuz uns die leibliche Unsterblichkeit zurückgibt, wodurch der biologische Zelltod blockiert wird."
            ),
            correctIndex = 1,
            explanation = "Das Gesetz ist heilig, gerecht und gut (Röm 7:12). Der Fluch des Gesetzes ist die im Gesetz definierte Strafe für Sünde, welche der Tod ist (5. Mose 27:26, Röm 6:23). Christus trug diese Todesstrafe stellvertretend für uns.",
            difficulty = "Mittel"
        ),
        QuizQuestion(
            id = 21,
            question = "An welcher prophetischen Stelle im Alten Testament wird der künftige Sukkot-Zwang für alle Nationen proklamiert?",
            options = listOf(
                "In der bekannten messianischen Leidenschaftsprophetie Jesajas, welche den Sühnetod des Gottesknechtes detailliert schildert.",
                "Im prophetischen Buch Sacharja (Kapitel 14, Verse 16 bis 18), wo Heidenvölker bei Strafe von Dürre zu Sukkot geladen sind.",
                "In den visionären Tiergeschichten im Buch des Propheten Daniel, welches die Vernichtung aller Tora-Feinde ankündigt.",
                "Im hebräischen Königshymnus des Psalmisten David, welcher die absolute Entweihung aller Feste in Heidenländern beklagt."
            ),
            correctIndex = 1,
            explanation = "Sacharja 14:16-18 prophezeit eindrücklich, dass alle übriggebliebenen Heidenvölker Jahr für Jahr nach Jerusalem pilgern werden, um den König Jahwe zu betreten und das Laubhüttenfest (Sukkot) zu feiern – bei Strafe von Dürre.",
            difficulty = "Experte"
        ),
        QuizQuestion(
            id = 22,
            question = "Welchen primären exegetischen Zweck erfüllt Petrus' dreimalige Tuchvision in der Apostelgeschichte?",
            options = listOf(
                "Dem Leiter der Urgemeinde die ausdrückliche Erlaubnis zum unbedenklichen Genuss von unreinen Raubtieren einzuräumen.",
                "Dem Apostel unmissverständlich aufzuzeigen, dass gottesfürchtige Heiden nicht mehr als unrein ausgegrenzt werden dürfen.",
                "Die theologische Grundlegung eines neuen heidenchristlichen Festkalenders unter Ausschluss des Sabbatgebots.",
                "Die prophetische Ankündigung der unwiderruflichen Zerstörung des jüdischen Urtempels und des gesamten Priestertums."
            ),
            correctIndex = 1,
            explanation = "Petrus selbst erklärt die Vision in Apg 10:28: 'Gott hat mir gezeigt, keinen MENSCHEN gemein oder unrein zu nennen.' Es war das grüne Licht für die Heidenmission und die Tischgemeinschaft mit nicht-jüdischen Gläubigen.",
            difficulty = "Mittel"
        ),
        QuizQuestion(
            id = 23,
            question = "Wer bezeichnet den wöchentlichen Sabbat in der Bundesurkunde (2. Mose 31) als 'ewigen Bund'?",
            options = listOf(
                "Mose, der als menschlicher Urheber die soziologischen Strukturen des jüdischen Volkes in der Wüste stabilisieren wollte.",
                "Der allmächtige Schöpfergott selbst, der den Sabbat als unaufhörliches Bundeszeichen zwischen sich und Seinem Volk deklariert.",
                "Der römische Heidenkaiser Konstantin, welcher im Zuge christlicher Konsolidierung die Staatsfeiertage neu anordnete.",
                "Die Rechtslehrer der pharisäischen Traditionen, welche den Sabbatgottesdienst zum exklusiven Heilsweg befördern wollten."
            ),
            correctIndex = 1,
            explanation = "In Exodus (2. Mose) 31:16-17 sagt Gott, dass die Kindern Israels den Sabbat halten sollen für all ihre Geschlechter als einen ewigen Bund (Berit Olam). Es ist ein Zeichen zwischen Ihm und Seinem Volk für immer.",
            difficulty = "Mittel"
        ),
        QuizQuestion(
            id = 24,
            question = "Welches Verbot erteilte das geschichtliche Konzil von Laodizäa in Kanon 29 bezüglich der Sabbatruhe?",
            options = listOf(
                "Es verpflichtete alle Christen im Ostreich unter Androhung harter Strafen zur Einhaltung des traditionellen jüdischen Sabbats.",
                "Es verbot Christen das Ruhen am Sabbat (Samstag) und forderte Arbeit am Sabbat, andernfalls seien sie von Christus ausgeschlossen.",
                "Es legalisierte die vollkommene Reinwaschung aller Speisen und unreinen Amphibien im gesamten römischen Herrschaftsgebiet.",
                "Es deklarierte das Recht des Papsttums zur willkürlichen Aufhebung moralischer Schöpfungsgesetze innerhalb der Ökumene."
            ),
            correctIndex = 1,
            explanation = "Kanon 29 verbot Christen ausdrücklich das Ruhen am Sabbat ('...sie sollen am Samstag nicht judäisieren, sondern an diesem Tag arbeiten... wenn sie aber als Judäisierende erfunden werden, so seien sie von Christus ausgeschlossen'). Dies beweist die fortlaufende historische Sabbatpraxis unter altkirchlichen Christen, die gewaltsam unterbunden wurde.",
            difficulty = "Experte"
        ),
        QuizQuestion(
            id = 25,
            question = "Aus welchem rechtlichen Grund darf Jeschua der Messias laut Hebräer 8:4 auf dieser Erde kein Priester sein?",
            options = listOf(
                "Weil der Messias aufgrund seiner vollkommenen Sündlosigkeit keine Tieropfer zur eigenen Sühne darbringen durfte.",
                "Weil das irdische Priesteramt laut Tora exklusiv den Leviten zusteht, Joshua jedoch dem davidischen Stamm Juda entstammte.",
                "Weil die administrative Militärgewalt der Römer im besetzten Judäa jegliche Tempelämter mit dem Tode bestrafte.",
                "Weil der überirdische Dienst nach Melchisedek den sofortigen Abriss und die Entweihung aller irdischen Tempelstufen forderte."
            ),
            correctIndex = 1,
            explanation = "Hebräer 8:4 sagt: 'Wenn er nun auf Erden wäre, so wäre er nicht einmal Priester, weil diejenigen da sind, die nach dem Gesetz die Gaben opfern.' Sein ewiges Priesteramt ist im himmlischen Heiligtum angesiedelt und weicht der irdischen Ordnung nicht aus.",
            difficulty = "Experte"
        ),
        QuizQuestion(
            id = 26,
            question = "Worum drehte sich historisch und literarisch der Konflikt im vielzitierten Kapitel Römer 14?",
            options = listOf(
                "Die administrative Entlassung des Sabbatgebots für christliche Hausgemeinden im pagangeschütteten antiken Rom.",
                "Die Streitfragen bezüglich aszetischer Fastentage und dem Gewissensverzehr von Marktfleisch, das Götzen geopfert wurde.",
                "Die unbeschränkte Erlaubnis zur Einführung von Schweinefleisch und Blutkonsum in die urchristliche Kulturpraxis.",
                "Die physische Tempelvernichtung in Jerusalem, welche die theologische Ungültigkeit des jüdischen Erbes demonstrieren sollte."
            ),
            correctIndex = 1,
            explanation = "In Römer 14 stritten Gläubige über vegetarischen Konsum (aus Furcht vor paganem Götzenopferfleisch) und freiwillige Fastentage, nicht über den göttlichen Sabbat oder die Speisegebote aus Levitikus 11.",
            difficulty = "Mittel"
        ),
        QuizQuestion(
            id = 27,
            question = "Welchen schriftlichen Urtextinhalt erhält der Neue Bund laut Hebräer 8:10 auf den fleischernen Herzentafeln?",
            options = listOf(
                "Die rabbinischen Zusatzgesetze der Traditionsältesten, welche das herkömmliche Volk von Generation zu Generation beladen hatten.",
                "Ein neues, stark verkürztes Liebesgebot der Gnade, welches die Gebote des Mose im alltäglichen Glaubensleben ablöst.",
                "Gottes eigene ewige Gesetze (die Tora), welche durch die Innewohnung des Heiligen Geistes verinnerlicht und geliebt werden.",
                "Ein moralisches Lehrsystem, welches im Geist durch den Glauben an Christus vollkommen formlos und unstrukturiert abläuft."
            ),
            correctIndex = 2,
            explanation = "Gott sagt ausdrücklich: 'Ich will meine Gesetze (hebr. Tora) in ihren Sinn geben und auf ihre Herzen schreiben.' Die Tora wird nicht anulliert, sondern durch den Heiligen Geist verinnerlicht.",
            difficulty = "Einsteiger"
        ),
        QuizQuestion(
            id = 28,
            question = "Mit welchen beiden Kontrahenten wurde der Neue Bund im Urtext (Hebräer 8:8) historisch einzig geschlossen?",
            options = listOf(
                "Mit dem heidnischen Kaiserreich Rom und der staatlichen Universalkirche im Sinne einer weltumspannenden Staatsreligion.",
                "Ausschließlich mit dem Hause Israel und mit dem Hause Juda (Jeremia 31), in welche nichtjüdische Gläubige eingepfropft werden.",
                "Mit allen antiken asiatischen Provinzen, um die Kreation eines vom mosaischen Erbe losgelösten Völkerbundes zu begründen.",
                "Einzig mit dem Apostel Paulus und den urchristlichen Diözesen, um das zeremonielle Gesetz des Ersten Bundes friedlich abzulösen."
            ),
            correctIndex = 1,
            explanation = "Sowohl in Jer 31:31 als auch in Heb 8:8 steht geschrieben, dass der Neue Bund mit dem 'Haus Israel und dem Haus Juda' geschlossen wird. Heiden müssen durch den Glauben eingepfropft werden (Röm 11).",
            difficulty = "Mittel"
        ),
        QuizQuestion(
            id = 29,
            question = "Inwiefern vollzog Jesus der Messias (Jeschua) historisch die alttestamentlichen Frühlingsfeste am Tempel?",
            options = listOf(
                "Überhaupt nicht, da er diese rituellen Schattenbilder zeitlebens als überholt ablehnte und den paganen Kalender duldete.",
                "Rein symbolisch und metaphernreich an beliebigen Wochentagen, um die Äußerlichkeit des jüdischen Festjahres zu relativieren.",
                "Präzise auf den Tag genau (Tod exakt zu Passah, Grabesruhe zu ungesäuerten Broten, Auferstehung zu Erstlingsgabe und Geistguss zu Pfingsten).",
                "Indem er das christliche Weihnachtsfest am Geburtstag des Sol Invictus als neuen, exklusiven Ruhetag der Gemeinde einsetzte."
            ),
            correctIndex = 2,
            explanation = "Jeschua starb exakt am Tag der Schlachtung des Passahlamms, ruhte im Grab an den ungesäuerten Broten, stand auf am Fest der Erstlinge (Erstlingsfrucht) und goss Seine Kraft an Schawuot (Pfingsten) aus.",
            difficulty = "Mittel"
        ),
        QuizQuestion(
            id = 30,
            question = "Welche drakonische Konsequenz droht Sacharja 14 jenen Nationen, die im künftigen Friedensreich Sukkot verweigern?",
            options = listOf(
                "Der sofortige Entzug aller Heilszusagen und die Verbannung in das ewige Verdammnisfeuer weitab des irdischen Paradieses.",
                "Ausnahmslose Dürre ohne jeglichen Regen für das gesamte Staatsgebiet der verweigernden, ungehorsamen Heidennation.",
                "Die Zwangsunterwerfung unter eine administrative Militärregierung, welche die Einhaltung drakonischer Steuern überwacht.",
                "Überhaupt keine Konsequenz, da der Prophet Sacharja hier über ein rein symbolisches, freiwilliges Gedenkfest im Himmel lehrt."
            ),
            correctIndex = 1,
            explanation = "Sacharja prophezeit, dass alle Nationen, die gegen Jerusalem gekämpft haben, hinaufziehen müssen, um Sukkot zu feiern, ansonsten wird auf ihr Land kein rettender Regen fallen.",
            difficulty = "Experte"
        ),
        QuizQuestion(
            id = 31,
            question = "Mit welcher Feststellung in 1. Korinther 7:19 bekräftigt Paulus die dauerhafte Geltung des Tora-Lebensstils?",
            options = listOf(
                "Mit dem Hinweis, dass die Gebote des Schöpfers durch das Vertrauen des Glaubens restlos außer Kraft gesetzt wurden.",
                "Mit dem Satz, dass die rituelle Beschneidung nichts ist, sondern allein das Halten der Gebote Gottes Relevanz besitzt.",
                "Mit der Behauptung, dass die physische Befolgung der Speisegesetze für Heidenchristen eine Verletzung der im Evangelium geschenkten Freiheit sei.",
                "Mit dem Appell, im Alltag ausschließlich paulinisches Briefgut zu memorieren, um das mosaische Gesetz reibungsfrei zu meiden."
            ),
            correctIndex = 1,
            explanation = "Paulus schreibt: 'Die Beschneidung ist nichts, und das Unbeschnittensein ist nichts, sondern das Halten der Gebote Gottes.' Er trennt theologische Konvertiten-Riten von der Gehorsamspflicht.",
            difficulty = "Mittel"
        ),
        QuizQuestion(
            id = 32,
            question = "Von welchem hebräischen Verb leitet sich das theologische Wort 'Tora' sprachwissenschaftlich ab?",
            options = listOf(
                "Von dem Verb 'Yarah', welches das exakte Anvisieren eines Ziels, das Schießen eines Pfeils oder das Erteilen von Wegweisung meint.",
                "Von der nominalen Wurzel 'Satan', welche den Ankläger im Gerichtsprozess deklariert und den Sünder unter Verdammung stellt.",
                "Von der rituellen Wurzel 'Baruch', welche das herbeigesehnte Erteilen von Segen bei akkurater Ausübung fleischlicher Werke untermauert.",
                "Von dem substantiellen Nomen 'Shalom', welches die Abwesenheit von Verordnungen und die vollkommene Willensfreiheit beschreibt."
            ),
            correctIndex = 0,
            explanation = "Tora kommt von 'Yarah', was bedeutet: in eine Richtung weisen, ein Ziel treffen, Führer sein oder einen Pfeil genau ins Schwarze schießen. Sünde (Chet) hingegen bedeutet, das Ziel zu verfehlen.",
            difficulty = "Experte"
        ),
        QuizQuestion(
            id = 33,
            question = "Inwiefern erfüllte der Messias 'alle Gerechtigkeit' bei seiner Taufe in Matthäus 3:15 laut der Wortbedeutung?",
            options = listOf(
                "Indem er die Relevanz jeglicher Rechtsordnung vor Gott für alle künftigen Zeiten vollständig deaktivierte.",
                "Indem er die Gerechtigkeit in makellosem, vorbildlichem Gehorsam auslebte und uns zur göttlichen Nachahmung demonstrierte.",
                "Indem er eine sündhafte Natur aufnahm, um die Unvollkommenheit des irdischen Gesetzes am eigenen Leibe zu sühnen.",
                "Indem er das levitische Priestertum und den steinernen Tempel als unzureichende Heilsmedien vor dem himmlischen Thron verwarf."
            ),
            correctIndex = 1,
            explanation = "Mithilfe von 'pleroo' drückt Matthäus aus, dass die Gerechtigkeit in ihrer vollkommenen Praxis demonstriert wurde. Gerechtigkeit wurde danach keineswegs abgeschafft, sondern vorbildlich etabliert.",
            difficulty = "Einsteiger"
        ),
        QuizQuestion(
            id = 34,
            question = "Welches umfassende Heilsversprechen gibt Gott in Jesaja 56:6-7 für sabbatwahrende Fremdlinge?",
            options = listOf(
                "Er verdammt sie als Tora-Diebe und untersagt ihnen die Passage zum heiligen inneren Vorhof des himmlischen Heiligtums.",
                "Er wird sie zu Seinem heiligen Berg bringen und sie in Seinem Bethaus mit Freude sowie mit der Annahme ihrer Opfer beschenken.",
                "Er fordert eine vorherige physische Konversion und Wassertaufe, um das Betreten des Jerusalemer Tempels überhaupt zu gestatten.",
                "Er verweist sie in den äußeren Vorhof der unbeschnittenen Völker, da der Sabbat exklusiv für Hebräer reserviert bleibt."
            ),
            correctIndex = 1,
            explanation = "Jesaja 56 offenbart, dass Gott Fremdlinge, die sich an Ihn klammern und Seinen Sabbat nicht entweihen, erfreuen und ihre Opfer auf Seinem Altar mit Wohlgefallen annehmen wird.",
            difficulty = "Mittel"
        ),
        QuizQuestion(
            id = 35,
            question = "Welchen bleibenden Status erhält ein Lehrer, der das Brechen selbst kleinster Gebote lehrt, laut Matthäus 5:19?",
            options = listOf(
                "Er wird mit sofortiger Wirkung des Amtes enthoben und als gesetzloser Gesell in das ewige Verdammnisfeuer verbannt.",
                "Er wird der 'Geringste' im Himmelreich genannt werden, während derjenige, der sie tut und lehrt, als 'Groß' bezeichnet wird.",
                "Der Einlass in das Himmelreich bleibt ihm komplett versagt, da dort ausschließlich makellose Tora-Befolger Zutritt finden.",
                "Er wird daselbst die glanzvolle Krone der Befreiungsweisheit empfangen, da er die Menschen von mosaischer Last entbunden hat."
            ),
            correctIndex = 1,
            explanation = "Jesus sagt: 'Wer nun eines dieser geringsten Gebote auflöst und die Menschen so lehrt, der wird der Geringste heißen im Himmelreich; wer sie aber tut und lehrt, der wird groß heißen.'",
            difficulty = "Einsteiger"
        )
    )
}

