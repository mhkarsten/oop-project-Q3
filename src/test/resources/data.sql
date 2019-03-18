INSERT INTO users (id, name, points) VALUES (1, 'Alex', 100);
INSERT INTO users (id, name, points) VALUES (2, 'Jim', 200);
INSERT INTO users (id, name, points) VALUES (3, 'Jennifer', 150);

INSERT INTO achievements (id, title, description, path) VALUES (1, 'GoGreen', 'Start GoGreen', 'path');
INSERT INTO achievements (id, title, description, path) VALUES (2, 'Vegan!', 'Eat a vegan meal', 'path');
INSERT INTO achievements (id, title, description, path) VALUES (3, 'P10nts', 'Achieve 10 points', 'path');

INSERT INTO user_achievement (user_id, achievement_id) VALUES (1,1);
INSERT INTO user_achievement (user_id, achievement_id) VALUES (1,2);
INSERT INTO user_achievement (user_id, achievement_id) VALUES (2,1);
INSERT INTO user_achievement (user_id, achievement_id) VALUES (3,1);
INSERT INTO user_achievement (user_id, achievement_id) VALUES (3,2);
INSERT INTO user_achievement (user_id, achievement_id) VALUES (3,3);

INSERT INTO followers (follower, followed) VALUES (1,3);
INSERT INTO followers (follower, followed) VALUES (1,2);
INSERT INTO followers (follower, followed) VALUES (2,1);
INSERT INTO followers (follower, followed) VALUES (2,3);
INSERT INTO followers (follower, followed) VALUES (3,1);
