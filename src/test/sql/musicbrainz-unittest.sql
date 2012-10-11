SET search_path = 'musicbrainz';

ALTER TABLE artist DISABLE TRIGGER b_del_artist_special;

DELETE FROM medium;
DELETE FROM track;
DELETE FROM tracklist;
DELETE FROM recording_gid_redirect;
DELETE FROM recording;
DELETE FROM track_name;
DELETE FROM release_gid_redirect;
DELETE FROM release;
DELETE FROM release_group_gid_redirect;
DELETE FROM release_group;
DELETE FROM release_group_primary_type;
DELETE FROM release_status;
DELETE FROM release_group;
DELETE FROM release_name;
DELETE FROM artist_credit;
DELETE FROM artist_credit_name;
DELETE FROM artist_gid_redirect;
DELETE FROM artist;
DELETE FROM artist_name;

ALTER TABLE artist ENABLE TRIGGER b_del_artist_special;

-- musicbrainz.release_status

INSERT INTO release_status (id, name) VALUES (1, 'Official');
INSERT INTO release_status (id, name) VALUES (2, 'Promotion');
INSERT INTO release_status (id, name) VALUES (3, 'Bootleg');
INSERT INTO release_status (id, name) VALUES (4, 'Pseudo-Release');

-- musicbrainz.release_group_primary_type

INSERT INTO release_group_primary_type (id, name) VALUES (1, 'Album');
INSERT INTO release_group_primary_type (id, name) VALUES (2, 'Single');
INSERT INTO release_group_primary_type (id, name) VALUES (3, 'EP');
INSERT INTO release_group_primary_type (id, name) VALUES (8, 'Audiobook');
INSERT INTO release_group_primary_type (id, name) VALUES (11, 'Other');

--

INSERT INTO artist_name (id, name) VALUES (1, 'Q and Not U');
INSERT INTO artist (id, gid, name, sort_name) VALUES (1, '994fcd41-2831-4318-9825-66bacbcf2cfe', 1, 1);
INSERT INTO artist_gid_redirect (new_id, gid) VALUES (1, 'a934e33f-b3cb-47dd-9638-f7f1f25fe162');
UPDATE artist SET last_updated = '2012-04-10 14:00:00' WHERE id = 1;

INSERT INTO artist_name (id, name) VALUES (2, 'Mono');
INSERT INTO artist (id, gid, name, sort_name, comment) VALUES (2, '194fcd41-2831-4318-9825-66bacbcf2cfe', 2, 2, 'Uk');
INSERT INTO artist (id, gid, name, sort_name, comment) VALUES (3, '294fcd41-2831-4318-9825-66bacbcf2cfe', 2, 2, 'Jp');

INSERT INTO artist_name (id, name) VALUES (3, 'Hot Chip');
INSERT INTO artist (id, gid, name, sort_name) VALUES (4, 'd8915e13-d67a-4aa0-9c0b-1f126af951af', 3, 3);
INSERT INTO artist_credit (id, name, artist_count) VALUES (2, 3, 1);
INSERT INTO artist_credit_name (artist_credit, artist, position, join_phrase, name) VALUES (2, 4, 1, '', 3);

INSERT INTO artist_name (id, name) VALUES (4, 'Rick Astley');
INSERT INTO artist (id, gid, name, sort_name) VALUES (5, 'db92a151-1ac2-438b-bc43-b82e149ddd50', 4, 4);
INSERT INTO artist_credit (id, name, artist_count) VALUES (1, 4, 1);
INSERT INTO artist_credit_name (artist_credit, artist, position, join_phrase, name) VALUES (1, 5, 1, '', 4);

INSERT INTO tracklist (id) VALUES (1);

INSERT INTO track_name (id, name) VALUES (1, 'The Saint');
INSERT INTO recording (id, artist_credit, gid, name, length) VALUES (1, 1, '4ea1383f-aca7-4a39-9839-576cf3af438b', 1, 1000);
INSERT INTO recording_gid_redirect (new_id, gid) VALUES (1, '6a27908d-9663-4e61-9ef3-e8b82c31dc14');
UPDATE recording SET last_updated = '2012-04-10 14:00:00' WHERE id = 1;
INSERT INTO track (id, tracklist, artist_credit, name, position, number, recording) VALUES (1, 1, 1, 1, 1, '1', 1);

INSERT INTO track_name (id, name) VALUES (2, 'The Sinner');
INSERT INTO recording (id, artist_credit, gid, name) VALUES (2, 1, '2ea1383f-aca7-4a39-9839-576cf3af438b', 2);
INSERT INTO track (id, tracklist, artist_credit, name, position, number, recording) VALUES (2, 1, 1, 2, 1, '1', 2);

INSERT INTO track_name (id, name) VALUES (3, 'Never Gonna Give You Up');
INSERT INTO recording (id, artist_credit, gid, name) VALUES (3, 1, '770cc467-8dde-4d22-bc4c-a42f91e7515e', 3);
INSERT INTO recording (id, artist_credit, gid, name) VALUES (4, 1, 'a45eb41b-5005-41b8-a0c9-17ba56ee3635', 3);

INSERT INTO release_name (id, name) VALUES (1, 'The Best of Rick Astley');
INSERT INTO release_group (id, artist_credit, gid, name, comment) VALUES (1, 1, 'a1f5f807-3851-48fb-838b-fb8a069f53e7', 1, 'release_group comment');
UPDATE release_group SET last_updated = '2012-04-10 14:00:00' WHERE id = 1;
INSERT INTO release_group_gid_redirect (new_id, gid) VALUES (1, '188711ed-c99b-439c-844a-ca831f63a727');
INSERT INTO release (id, artist_credit, gid, release_group, name, date_year, date_month, date_day) VALUES (1, 1, 'e1f5f807-3851-48fb-838b-fb8a069f53e7', 1, 1, null, 10, 11);

INSERT INTO release_name (id, name) VALUES (2, 'The Warning');
INSERT INTO release_group (id, artist_credit, gid, name, type) VALUES (2, 2, '4c616513-9b14-3dfd-b023-a7e77e69a029', 2, 1);
INSERT INTO release (id, artist_credit, gid, release_group, name, date_year, date_month, date_day) VALUES (2, 2, 'f80addb0-1f8c-3c37-a4a9-6f8867be35fe', 2, 2, 2006, 05, 22);
INSERT INTO release (id, artist_credit, gid, release_group, name, date_year, date_month, date_day) VALUES (3, 2, '6ff468ec-c06d-3e90-b0eb-529af92784b7', 2, 2, 2006, 05, null);

INSERT INTO release_name (id, name) VALUES (3, 'One Life Stand');
INSERT INTO release_group (id, artist_credit, gid, name) VALUES (3, 2, 'e83f684b-bc49-4ea2-91c4-b1583c741829', 3);
INSERT INTO release (id, artist_credit, gid, release_group, name, date_year, date_month, date_day, status) VALUES (4, 2, '5ced615f-cd92-3b08-b3cb-5971e5bd6eb5', 3, 3, 2010, null, null, 1);

-- for unit testing ArtistCredit, Medium

INSERT INTO artist_name (id, name) VALUES (5, 'Mono and Rick Astley feat. Hot Chip');
INSERT INTO artist_credit (id, name, artist_count) VALUES (3, 5, 3);
INSERT INTO artist_credit_name (artist_credit, artist, position, join_phrase, name) VALUES (3, 4, 3, '', 3);
INSERT INTO artist_credit_name (artist_credit, artist, position, join_phrase, name) VALUES (3, 2, 1, ' and ', 2);
INSERT INTO artist_credit_name (artist_credit, artist, position, join_phrase, name) VALUES (3, 5, 2, ' feat. ', 4);

INSERT INTO release_name (id, name) VALUES (4, 'Multi-Disc Extravaganza');
INSERT INTO release_group (id, artist_credit, gid, name) VALUES (4, 3, 'f94f795c-cd59-4ea2-91c4-b1583c741829', 4);
INSERT INTO release (id, artist_credit, gid, release_group, name, date_year, date_month, date_day) VALUES (5, 3, '6dfe725f-de93-3b08-b3cb-5971e5bd6eb5', 4, 4, 2011, 07, 23);
INSERT INTO release_gid_redirect (new_id, gid) VALUES (5, '5d32bacc-d62a-4e77-9f0e-d934e53d5359');
UPDATE release SET last_updated = '2012-04-10 14:00:00' WHERE id = 5;

INSERT INTO tracklist (id) VALUES (3);
INSERT INTO medium (id, tracklist, release, position) VALUES (3, 3, 5, 2);
INSERT INTO tracklist (id) VALUES (2);
INSERT INTO medium (id, tracklist, release, position, name) VALUES (2, 2, 5, 1, 'Disc 1');
UPDATE medium SET last_updated = '2012-04-10 14:00:00' WHERE id = 2;
INSERT INTO track (id, tracklist, artist_credit, name, position, number, recording) VALUES (4, 2, 2, 2, 2, '2', 2);
INSERT INTO track (id, tracklist, artist_credit, name, position, number, recording) VALUES (3, 2, 1, 1, 1, '1', 1);
