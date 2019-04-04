INSERT INTO users (id, name, password)
VALUES (user_seq.NEXTVAL, 'Alex','$2y$11$wqDyg7JvtKJqBo2HNSrfP.pqc5q0SCCOhk.irp6kL1yZ56nfMg00S');
INSERT INTO users (id, name,password)
VALUES (user_seq.NEXTVAL, 'Jim','asdf');
INSERT INTO users (id, name,password)
VALUES (user_seq.NEXTVAL, 'Jennifer','asdf');

INSERT INTO achievements (id, title, description, path)
VALUES (achievement_seq.NEXTVAL, 'GoGreen', 'Start GoGreen', 'path');
INSERT INTO achievements (id, title, description, path)
VALUES (achievement_seq.NEXTVAL, 'Vegan!', 'Eat a vegan meal', 'path');
INSERT INTO achievements (id, title, description, path)
VALUES (achievement_seq.NEXTVAL, 'P10nts', 'Achieve 10 points', 'path');

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
VALUES (feat_seq.NEXTVAL,420, 1, 2, CURRENT_TIMESTAMP);
INSERT INTO feats (id, points, action_id, user_id, time_completed)
VALUES (feat_seq.NEXTVAL,134, 3, 1, CURRENT_TIMESTAMP);
INSERT INTO feats (id, points, action_id, user_id, time_completed)
VALUES (feat_seq.NEXTVAL,633, 2, 1, CURRENT_TIMESTAMP);
INSERT INTO feats (id, points, action_id, user_id, time_completed)
VALUES (feat_seq.NEXTVAL,261, 5, 3, CURRENT_TIMESTAMP);
INSERT INTO feats (id, points, action_id, user_id, time_completed)
VALUES (feat_seq.NEXTVAL,278, 6, 3, CURRENT_TIMESTAMP);
INSERT INTO feats (id, points, action_id, user_id, time_completed)
VALUES (feat_seq.NEXTVAL,421, 1, 2, CURRENT_TIMESTAMP);
INSERT INTO feats (id, points, action_id, user_id, time_completed)
VALUES (feat_seq.NEXTVAL,856, 1, 1, CURRENT_TIMESTAMP);