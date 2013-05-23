SET search_path = 'musicbrainz';

DELETE FROM medium;
DELETE FROM track;
DELETE FROM recording_gid_redirect;
DELETE FROM recording;
DELETE FROM track_name;
DELETE FROM release_gid_redirect;
DELETE FROM release;
DELETE FROM release_group_gid_redirect;
DELETE FROM release_group;
DELETE FROM release_name;
DELETE FROM release_group_primary_type;
DELETE FROM release_status;
DELETE FROM release_unknown_country;
DELETE FROM release_country;
DELETE FROM artist_credit;
DELETE FROM artist_credit_name;
DELETE FROM artist_gid_redirect;
DELETE FROM artist;
DELETE FROM artist_name;
DELETE FROM artist_type;
DELETE FROM gender;
DELETE FROM area_gid_redirect;
DELETE FROM area;
DELETE FROM area_type;

-- musicbrainz.artist_type

INSERT INTO artist_type (id, name) VALUES (1, 'Person');
INSERT INTO artist_type (id, name) VALUES (2, 'Group');
INSERT INTO artist_type (id, name) VALUES (3, 'Other');

-- musicbrainz.gender

INSERT INTO gender (id, name) VALUES (1, 'Male');
INSERT INTO gender (id, name) VALUES (2, 'Female');
INSERT INTO gender (id, name) VALUES (3, 'Other');

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

-- musicbrainz.area_type

INSERT INTO area_type (id, name) VALUES (1, 'Country');
INSERT INTO area_type (id, name) VALUES (2, 'Subdivision');

-- musicbrainz.area

INSERT INTO area (id, gid, name, sort_name, type, begin_date_year, begin_date_month, begin_date_day, end_date_year, end_date_month, end_date_day) VALUES (1178, 'f03d09b3-39dc-4083-afd6-159e3f0d462f', 'London', 'London', null, null, null, null, null, null, null);
INSERT INTO area (id, gid, name, sort_name, type, begin_date_year, begin_date_month, begin_date_day, end_date_year, end_date_month, end_date_day) VALUES (397, '8dc97297-ac95-4d33-82bc-e07fab26fb5f', 'Tokyo', 'Tokyo', 2, null, null, null, null, null, null);

INSERT INTO area (id, gid, name, sort_name, type, begin_date_year, begin_date_month, begin_date_day, end_date_year, end_date_month, end_date_day, ended) VALUES (151, 'c741c28e-cbec-3977-88c8-583a8af62522', 'Netherlands Antilles', 'Netherlands Antilles', 1, 1954, 9, 12, 2010, 12, 9, true);
INSERT INTO area_gid_redirect (new_id, gid) VALUES (151, '6b43e5f2-49e8-46ce-94cb-a9b23e5bb4e8');
INSERT INTO area (id, gid, name, sort_name, type, begin_date_year, begin_date_month, begin_date_day, end_date_year, end_date_month, end_date_day) VALUES (113, 'b9f7d640-46e8-313e-b158-ded6d18593b3', 'South Korea', 'South Korea', 1, null, null, null, null, null, null);

UPDATE area SET last_updated = '2013-05-19 20:23:54' WHERE id = 1178;
UPDATE area SET last_updated = '2013-05-15 16:49:41' WHERE id = 151;

--

INSERT INTO artist_name (id, name) VALUES (1, 'Q and Not U');
INSERT INTO artist (id, gid, name, sort_name, begin_date_year, begin_date_month, begin_date_day, end_date_year, end_date_month, end_date_day, area, comment, ended, begin_area, end_area) VALUES (1, '994fcd41-2831-4318-9825-66bacbcf2cfe', 1, 1, 1950, 2, 3, 2001, 4, 5, 397, 'Soft Pyramids', true, 113, 151);
INSERT INTO artist_gid_redirect (new_id, gid) VALUES (1, 'a934e33f-b3cb-47dd-9638-f7f1f25fe162');
UPDATE artist SET last_updated = '2012-04-10 14:00:00' WHERE id = 1;

INSERT INTO artist_name (id, name) VALUES (2, 'Mono');
INSERT INTO artist (id, gid, name, sort_name, comment) VALUES (2, '194fcd41-2831-4318-9825-66bacbcf2cfe', 2, 2, 'Uk');
INSERT INTO artist (id, gid, name, sort_name, comment) VALUES (3, '294fcd41-2831-4318-9825-66bacbcf2cfe', 2, 2, 'Jp');

INSERT INTO artist_name (id, name) VALUES (3, 'Hot Chip');
INSERT INTO artist (id, gid, name, sort_name, type) VALUES (4, 'd8915e13-d67a-4aa0-9c0b-1f126af951af', 3, 3, 2);
INSERT INTO artist_credit (id, name, artist_count) VALUES (2, 3, 1);
INSERT INTO artist_credit_name (artist_credit, artist, position, join_phrase, name) VALUES (2, 4, 1, '', 3);

INSERT INTO artist_name (id, name) VALUES (4, 'Rick Astley');
INSERT INTO artist (id, gid, name, sort_name, type, gender) VALUES (5, 'db92a151-1ac2-438b-bc43-b82e149ddd50', 4, 4, 1, 1);
INSERT INTO artist_credit (id, name, artist_count) VALUES (1, 4, 1);
INSERT INTO artist_credit_name (artist_credit, artist, position, join_phrase, name) VALUES (1, 5, 1, '', 4);

INSERT INTO track_name (id, name) VALUES (1, 'The Saint');
INSERT INTO recording (id, artist_credit, gid, name, length) VALUES (1, 1, '4ea1383f-aca7-4a39-9839-576cf3af438b', 1, 1000);
INSERT INTO recording_gid_redirect (new_id, gid) VALUES (1, '6a27908d-9663-4e61-9ef3-e8b82c31dc14');
UPDATE recording SET last_updated = '2012-04-10 14:00:00' WHERE id = 1;
INSERT INTO track (id, gid, medium, artist_credit, name, position, number, recording) VALUES (1, '70c4bd53-f3ef-354f-97a9-7ed76915087a', 1, 1, 1, 1, '1', 1);

INSERT INTO track_name (id, name) VALUES (2, 'The Sinner');
INSERT INTO recording (id, artist_credit, gid, name) VALUES (2, 1, '2ea1383f-aca7-4a39-9839-576cf3af438b', 2);
INSERT INTO track (id, gid, medium, artist_credit, name, position, number, recording) VALUES (2, 'a91e066b-4101-3f96-91e2-57b5d0dead60', 1, 1, 2, 1, '1', 2);

INSERT INTO track_name (id, name) VALUES (3, 'Never Gonna Give You Up');
INSERT INTO recording (id, artist_credit, gid, name) VALUES (3, 1, '770cc467-8dde-4d22-bc4c-a42f91e7515e', 3);
INSERT INTO recording (id, artist_credit, gid, name) VALUES (4, 1, 'a45eb41b-5005-41b8-a0c9-17ba56ee3635', 3);

INSERT INTO release_name (id, name) VALUES (1, 'The Best of Rick Astley');
INSERT INTO release_group (id, artist_credit, gid, name, comment) VALUES (1, 1, 'a1f5f807-3851-48fb-838b-fb8a069f53e7', 1, 'release_group comment');
UPDATE release_group SET last_updated = '2012-04-10 14:00:00' WHERE id = 1;
INSERT INTO release_group_gid_redirect (new_id, gid) VALUES (1, '188711ed-c99b-439c-844a-ca831f63a727');
INSERT INTO release (id, artist_credit, gid, release_group, name) VALUES (1, 1, 'e1f5f807-3851-48fb-838b-fb8a069f53e7', 1, 1);

INSERT INTO release_name (id, name) VALUES (2, 'The Warning');
INSERT INTO release_group (id, artist_credit, gid, name, type) VALUES (2, 2, '4c616513-9b14-3dfd-b023-a7e77e69a029', 2, 1);
INSERT INTO release (id, artist_credit, gid, release_group, name) VALUES (2, 2, 'f80addb0-1f8c-3c37-a4a9-6f8867be35fe', 2, 2);
INSERT INTO release (id, artist_credit, gid, release_group, name) VALUES (3, 2, '6ff468ec-c06d-3e90-b0eb-529af92784b7', 2, 2);

INSERT INTO release_name (id, name) VALUES (3, 'One Life Stand');
INSERT INTO release_group (id, artist_credit, gid, name) VALUES (3, 2, 'e83f684b-bc49-4ea2-91c4-b1583c741829', 3);
INSERT INTO release (id, artist_credit, gid, release_group, name, status) VALUES (4, 2, '5ced615f-cd92-3b08-b3cb-5971e5bd6eb5', 3, 3, 1);
INSERT INTO release_country (release, country, date_year, date_month, date_day) VALUES (4, 151, null, 7, 23);
INSERT INTO release_country (release, country, date_year, date_month, date_day) VALUES (4, 113, 2010, 7, 23);

-- for unit testing ArtistCredit, Medium

INSERT INTO artist_name (id, name) VALUES (5, 'Mono and Rick Astley feat. Hot Chip');
INSERT INTO artist_credit (id, name, artist_count) VALUES (3, 5, 3);
INSERT INTO artist_credit_name (artist_credit, artist, position, join_phrase, name) VALUES (3, 4, 3, '', 3);
INSERT INTO artist_credit_name (artist_credit, artist, position, join_phrase, name) VALUES (3, 2, 1, ' and ', 2);
INSERT INTO artist_credit_name (artist_credit, artist, position, join_phrase, name) VALUES (3, 5, 2, ' feat. ', 4);

INSERT INTO release_name (id, name) VALUES (4, 'Multi-Disc Extravaganza');
INSERT INTO release_group (id, artist_credit, gid, name) VALUES (4, 3, 'f94f795c-cd59-4ea2-91c4-b1583c741829', 4);
INSERT INTO release (id, artist_credit, gid, release_group, name) VALUES (5, 3, '6dfe725f-de93-3b08-b3cb-5971e5bd6eb5', 4, 4);
INSERT INTO release_gid_redirect (new_id, gid) VALUES (5, '5d32bacc-d62a-4e77-9f0e-d934e53d5359');
INSERT INTO release_unknown_country (release, date_year, date_month, date_day) VALUES (5, 2011, 7, 23);
UPDATE release SET last_updated = '2012-04-10 14:00:00' WHERE id = 5;

INSERT INTO medium (id, release, position) VALUES (3, 5, 2);
INSERT INTO medium (id, release, position, name) VALUES (2, 5, 1, 'Disc 1');
UPDATE medium SET last_updated = '2012-04-10 14:00:00' WHERE id = 2;
INSERT INTO track (id, gid, recording, medium, position, number, name, artist_credit, length) VALUES (4, '9324fa33-3f60-3f63-80b9-02a5134a5dd9', 2, 2, 2, '2', 2, 2, 177760);
INSERT INTO track (id, gid, recording, medium, position, number, name, artist_credit, length) VALUES (3, '3c22f2da-6c1e-3f0a-baba-4147fede5eae', 1, 2, 1, '1', 1, 1, 254160); 
