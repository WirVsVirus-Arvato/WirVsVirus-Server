DELETE FROM question;
DELETE FROM questionnaire;
DELETE FROM text_lang;

INSERT INTO text_lang (id, language_id, "text") values
(1, 1,'Onboarding Fragebogen'),
(2, 1,'Fragebogen 1');
/*
(2, 1,'Welches Geschlecht haben sie?'),
(3, 1,'Sind sie Schwanger?'),
(4, 1,'Wie alt sind sie?'),
(5, 1,'Rauchen Sie?'),
(6, 1,'Hatten sie einen Kontakt zu einem bestätigten Corona Fall?'),
(7, 1,'Wie viele Tage Liegt das ca. zurück?'),
(8, 1,'Wie eng war der Kontakt?'),
(9, 1,'Sind sie innerhalb der letzten 4 Wochen verreist?'),
(10, 1,'Wohin?'),
(11,1, 'Haben sie sich im Oktober 2019 bis heute gegen Grippe Impfen lassen?')
(11, 1,'Nehmen sie aktuell folgende Medikamente ein?'),
(12, 1,'Welche Symptome hatten sie in den letzten 24 Stunden?'),
(13, 1,'Welche Symptome hatten sie in den letzten 4 Stunden?'),
(14, 1,'Arbeiten sie derzeit?'),
(15, 1,'Arbeiten sie derzeit im Homeoffice?'),
(16, 1,'Arbeiten sie derzeit mit anderen Menschen?'),
(17, 1,'Leben sie mit anderen Personen zusammen im Haushalt?'),
(18, 1,'Beschreiben sie ihre Wohnsituation?'),

(18, 1,'Fragebogen 1'),
(19, 1,'Wie geht es ihnen heute?'),
(20, 1,'Husten sie?');
*/
INSERT INTO questionnaire(id, title) VALUES
(1,1),
(2,2);
/*
INSERT INTO question(id, questionnaire_id, question_text,"type",payload) VALUES
(1,1,2,'MULTIPLECHOICE','[M,W]'),
(2,1,3,'MULTIPLECHOICE','[Ja,Nein]'),
(3,1,4,'MULTIPLECHOICE', '[<20,20-30,30-40,40-50,50-60,60-70,70-80,80>]'),
(4,1,5,'MULTIPLECHOICE','[Ja,Nein,Gelegentlich]').
(5,1,6,'MULTIPLECHOICE','[Ja,Nein,Verdachtsfall]'),
(6,1,7,'MULTIPLECHOICE','[1,2,3,4,5,6,7,8,9,10,10>]'),
(7,1,8,'MULTIPLECHOICE','[unter 1m, unter 2m, über 2m]'),
(8,1,9,'MULTIPLECHOICE','[Ja: innerhalb von Deutschland,Ja: außerhalb von Deutschland,Nein]'),
(10,1,10,'MULTIPLECHOICE','[Innerhalb der EU, Außerhalb der EU]')
(11,1,11,'MULTIPLECHOICE','[Ja, Nein]')
*/




CREATE OR REPLACE FUNCTION insert_multiple_choices(questionnaire_id bigint, question_text text,choices text[]) RETURNS void AS $$
declare
    m text;
    question_id BIGINT;
    text_id BIGINT;
BEGIN
    INSERT INTO text_lang (language_id, text) VALUES (1, question_text) RETURNING id INTO text_id;
    INSERT INTO question(questionnaire_id, question_text,"type") VALUES (questionnaire_id,text_id,'MULTIPLECHOICE') RETURNING id INTO question_id;
    FOREACH m IN ARRAY choices
        LOOP
          INSERT INTO text_lang (language_id, text) VALUES (1, m) RETURNING id INTO text_id;
          INSERT INTO multiple_choice_answer (question_id,answer) VALUES (question_id,text_id);
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




