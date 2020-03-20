INSERT INTO text_lang (id, language_id, "text") values
(1, 1,'Oboarding Fragebogen'),
(2, 1,'Wie alt sind sie?'),
(3, 1,'Wie ist Ihre aktuelle Wohnsituation?(Alleine, WG, Familie)'),
(4, 1,'Fragebogen 1'),
(5, 1,'Wie geht es ihnen heute?'),
(6, 1,'Husten sie?');

INSERT INTO questionnaire(id, text_id) VALUES
(1,1),
(2,4);

INSERT INTO question(id, questionnaire_id, type_id, text_id) VALUES
(1,1,1,2),
(2,1,1,3),
(3,1,1,5),
(4,1,1,6);


