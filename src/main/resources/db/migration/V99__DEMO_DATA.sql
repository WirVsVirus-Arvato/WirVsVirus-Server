DELETE FROM question;
DELETE FROM questionnaire;
DELETE FROM text_lang;

INSERT INTO people (token)
VALUES ('YJJ-UND'),
       ('IR4-1XT');

INSERT INTO text_lang (id, language_id, "text") values
(1, 1,'Onboarding Fragebogen'),
(2, 1,'Fragebogen 1');

INSERT INTO questionnaire(id, title) VALUES
(1,1),
(2,2);

CREATE OR REPLACE FUNCTION insert_multiple_choices(questionnaire_id_value bigint, question_text text,choices text[]) RETURNS void AS $$
declare
    m text;
    question_id_value BIGINT;
    text_id_value BIGINT;
BEGIN
    INSERT INTO text_lang (language_id, text) VALUES (1, question_text) RETURNING id INTO text_id_value;
    INSERT INTO question(questionnaire_id, question_text,"type") VALUES (questionnaire_id_value,text_id_value,'MULTIPLECHOICE') RETURNING id INTO question_id_value;
    FOREACH m IN ARRAY choices
        LOOP
            INSERT INTO text_lang (language_id, text) VALUES (1, m) RETURNING id INTO text_id_value;
            INSERT INTO multiple_choice_answer (question_id,answer) VALUES (question_id_value,text_id_value);
        END LOOP;
END;
$$ LANGUAGE plpgsql;

SELECT setval('text_lang_id_seq', (SELECT MAX(id) FROM text_lang)+1);
SELECT setval('question_id_seq', (SELECT MAX(id) FROM question)+1);

Select insert_multiple_choices(1,'Welches Geschlecht haben sie?',array['M','W']);
Select insert_multiple_choices(1,'Sind sie Schwanger?' ,array['Ja','Nein']);
Select insert_multiple_choices(1,'Wie alt sind sie?',array['<20','20-30','30-40','40-50','50-60','60-70','70-80','80>']);
Select insert_multiple_choices(1,'Rauchen Sie?',array['Ja','Nein','Gelegentlich']);
Select insert_multiple_choices(1,'Hatten sie einen Kontakt zu einem bestätigten Corona Fall?',array['Ja','Nein','Verdachtsfall']);
Select insert_multiple_choices(1,'Wie viele Tage Liegt dieser ca. zurück?',array['1','2','3','4','5','6','7', '7>']);
Select insert_multiple_choices(1,'Wie eng war der Kontakt?',array['unter 1 Meter','unter 2 Meter', 'über 2 Meter']);
Select insert_multiple_choices(1,'Sind sie innerhalb der letzten 4 Wochen verreist?',array['Ja','Nein']);
Select insert_multiple_choices(1,'Wohin?',array['Innerhalb von Europa','Außerhalb von Europa']);
Select insert_multiple_choices(1,'Haben sie sich im Oktober 2019 bis heute gegen Grippe Impfen lassen?',array['Ja','Nein']);
Select insert_multiple_choices(1,'Nehmen sie aktuell folgende Medikamente ein?',array['Ja','Nein']);
Select insert_multiple_choices(1,'Welche Symptome hatten sie in den letzten 24 Stunden?',array['Husten','Fieber','Laufende Nase']);
Select insert_multiple_choices(1,'Welche Symptome hatten sie in den letzten 4 Stunden?',array['Husten','Fieber','Laufende Nase']);
Select insert_multiple_choices(1,'Arbeiten sie derzeit?',array['Ja','Nein']);
Select insert_multiple_choices(1,'Arbeiten sie derzeit im Homeoffice?',array['Ja','Nein']);
Select insert_multiple_choices(1,'Arbeiten sie derzeit mit anderen Menschen?',array['Ja','Nein']);
Select insert_multiple_choices(1,'Leben sie mit anderen Menschen zusammen im Haushalt?',array['Ja','Nein']);
Select insert_multiple_choices(1,'Beschreiben sie ihre Wohnsituation?',array['Haus', 'Wohnung']);

INSERT INTO answer (question_id, people_id, content)
VALUES (1, (SELECT id FROM people WHERE token = 'YJJ-UND'), 'Anwort 1'),
       (2, (SELECT id FROM people WHERE token = 'YJJ-UND'), 'Anwort 2'),
       (3, (SELECT id FROM people WHERE token = 'YJJ-UND'), 'Anwort 3'),
       (4, (SELECT id FROM people WHERE token = 'YJJ-UND'), 'Anwort 4'),
       (1, (SELECT id FROM people WHERE token = 'IR4-1XT'), 'Anwort 5');

