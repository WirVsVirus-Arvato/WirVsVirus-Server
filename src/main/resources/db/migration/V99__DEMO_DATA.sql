INSERT INTO people (token)
VALUES ('YJJ-UND'),
       ('IR4-1XT');

INSERT INTO answer (question_id, people_id, content)
VALUES (1, (SELECT id FROM people WHERE token = 'YJJ-UND'), 'Anwort 1'),
       (2, (SELECT id FROM people WHERE token = 'YJJ-UND'), 'Anwort 2'),
       (3, (SELECT id FROM people WHERE token = 'YJJ-UND'), 'Anwort 3'),
       (4, (SELECT id FROM people WHERE token = 'YJJ-UND'), 'Anwort 4'),
       (1, (SELECT id FROM people WHERE token = 'IR4-1XT'), 'Anwort 5');

