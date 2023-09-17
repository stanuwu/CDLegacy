--
-- PostgreSQL database dump
--

-- Dumped from database version 14.0
-- Dumped by pg_dump version 14.0

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: guilds; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.guilds (
    guildid bigint NOT NULL,
    doors_opened bigint NOT NULL,
    monsters_slain bigint NOT NULL,
    bosses_slain bigint NOT NULL
);


ALTER TABLE public.guilds OWNER TO postgres;

--
-- Name: items; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.items (
    playerid bigint NOT NULL,
    name character varying NOT NULL,
    count bigint NOT NULL
);


ALTER TABLE public.items OWNER TO postgres;

--
-- Name: players; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.players (
    playerid bigint NOT NULL,
    name character varying(20) NOT NULL,
    title character varying NOT NULL,
    description character varying(80) NOT NULL,
    class character varying NOT NULL,
    exp bigint NOT NULL,
    coins bigint NOT NULL,
    weapon character varying NOT NULL,
    armor character varying NOT NULL,
    extra character varying NOT NULL,
    weapon_xp bigint NOT NULL,
    armor_xp bigint NOT NULL,
    extra_xp bigint NOT NULL,
    last_vote timestamp without time zone NOT NULL,
    quest character varying NOT NULL,
    quest_level integer NOT NULL,
    quest_progress integer NOT NULL,
    farming character varying NOT NULL,
    monsters_slain bigint NOT NULL,
    doors_opened bigint NOT NULL,
    bosses_slain bigint NOT NULL,
    items_found bigint NOT NULL,
    chests_opened bigint NOT NULL,
    deleted boolean NOT NULL
);


ALTER TABLE public.players OWNER TO postgres;

--
-- Data for Name: guilds; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.guilds (guildid, doors_opened, monsters_slain, bosses_slain) FROM stdin;
664017503176884234	0	0	0
\.


--
-- Data for Name: items; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.items (playerid, name, count) FROM stdin;
623984743914012712	STONE	5
623984743914012712	DIAMOND	1
\.


--
-- Data for Name: players; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.players (playerid, name, title, description, class, exp, coins, weapon, armor, extra, weapon_xp, armor_xp, extra_xp, last_vote, quest, quest_level, quest_progress, farming, monsters_slain, doors_opened, bosses_slain, items_found, chests_opened, deleted) FROM stdin;
623984743914012712	stan	DEVELOPER	Freddy Fortnite aint got nothin on me!	SAGE	0	31	STICK	RAGS	PENDANT	0	0	0	2000-01-01 00:00:00	NONE	1	0	FORAGING	0	0	0	0	0	f
677623766066659348	Freddy Facebook	N	uwu	THIEF	0	50	STICK	RAGS	PENDANT	0	0	0	2000-01-01 00:00:00	NONE	1	0	FORAGING	0	0	0	0	0	f
159355703016947713	armored core 6 fires	LEGACY	Der Antagonist ist ein Muskel und Gegenspieler des Agonisten. Das muskulre Zusam	ADVENTURER	0	50	STICK	RAGS	PENDANT	0	0	0	2000-01-01 00:00:00	NONE	1	0	MINING	0	0	0	0	0	f
255732879525412865	Coinbiter	PLAYER	Default Description	ADVENTURER	0	49	STICK	RAGS	PENDANT	0	0	0	2000-01-01 00:00:00	NONE	1	0	MINING	0	0	0	0	0	f
\.


--
-- Name: guilds guilds_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.guilds
    ADD CONSTRAINT guilds_pkey PRIMARY KEY (guildid);


--
-- Name: items items_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.items
    ADD CONSTRAINT items_pkey PRIMARY KEY (playerid, name);


--
-- Name: players players_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.players
    ADD CONSTRAINT players_pkey PRIMARY KEY (playerid);


--
-- PostgreSQL database dump complete
--

