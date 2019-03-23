INSERT INTO users (id, name)
VALUES (1, 'Alex');
INSERT INTO users (id, name)
VALUES (2, 'Jim');
INSERT INTO users (id, name)
VALUES (3, 'Jennifer');

INSERT INTO achievements (id, title, description, path)
VALUES (1, 'GoGreen', 'Start GoGreen', 'path');
INSERT INTO achievements (id, title, description, path)
VALUES (2, 'Vegan!', 'Eat a vegan meal', 'path');
INSERT INTO achievements (id, title, description, path)
VALUES (3, 'P10nts', 'Achieve 10 points', 'path');

INSERT INTO user_achievement (user_id, achievement_id)
VALUES (1, 1);
INSERT INTO user_achievement (user_id, achievement_id)
VALUES (1, 2);
INSERT INTO user_achievement (user_id, achievement_id)
VALUES (2, 1);
INSERT INTO user_achievement (user_id, achievement_id)
VALUES (3, 1);
INSERT INTO user_achievement (user_id, achievement_id) VALUES (3, 2);
INSERT INTO user_achievement (user_id, achievement_id) VALUES (3, 3);

INSERT INTO followers (follower, followed) VALUES (1, 3);
INSERT INTO followers (follower, followed) VALUES (1, 2);
INSERT INTO followers (follower, followed) VALUES (2, 1);
INSERT INTO followers (follower, followed) VALUES (2, 3);

INSERT INTO feats (id, points, action_id, user_id, time_completed)
VALUES (1,420, 1, 2, CURRENT_TIMESTAMP);
INSERT INTO feats (id, points, action_id, user_id, time_completed)
VALUES (2,134, 3, 1, CURRENT_TIMESTAMP);
INSERT INTO feats (id, points, action_id, user_id, time_completed)
VALUES (3,633, 2, 1, CURRENT_TIMESTAMP);
INSERT INTO feats (id, points, action_id, user_id, time_completed)
VALUES (4,261, 5, 1, CURRENT_TIMESTAMP);
INSERT INTO feats (id, points, action_id, user_id, time_completed)
VALUES (5,278, 6, 2, CURRENT_TIMESTAMP);
INSERT INTO feats (id, points, action_id, user_id, time_completed)
VALUES (6,421, 1, 2, CURRENT_TIMESTAMP);
INSERT INTO feats (id, points, action_id, user_id, time_completed)
VALUES (7,856, 1, 1, CURRENT_TIMESTAMP);