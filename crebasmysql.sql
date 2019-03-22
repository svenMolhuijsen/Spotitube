/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     21-3-2019 23:27:34                           */
/*==============================================================*/


drop table if exists PLAYLIST;

drop table if exists PLAYLIST_BEVAT_TRACKS;

drop table if exists TRACK;

drop table if exists USER;

/*==============================================================*/
/* Table: PLAYLIST                                              */
/*==============================================================*/
create table PLAYLIST
(
   PLAYLISTID           int not null auto_increment,
   NAME                 varchar(100) not null,
   USERNAME             varchar(100) not null,
   primary key (PLAYLISTID)
);

/*==============================================================*/
/* Table: PLAYLIST_BEVAT_TRACKS                                 */
/*==============================================================*/
create table PLAYLIST_BEVAT_TRACKS
(
   PLAYLISTID           int not null,
   TRACKID              int not null,
   primary key (PLAYLISTID, TRACKID)
);

/*==============================================================*/
/* Table: TRACK                                                 */
/*==============================================================*/
create table TRACK
(
   TRACKID              int not null auto_increment,
   TITLE                varchar(100) not null,
   PERFORMER            varchar(100) not null,
   DURATION             int not null,
   ALBUM                varchar(100) not null,
   PLAYCOUNT            int not null,
   PUBLICATIONDATE      datetime not null,
   DESCRIPTION          text,
   OFFLINEAVAILABLE     bool not null,
   primary key (TRACKID)
);

/*==============================================================*/
/* Table: USER                                                  */
/*==============================================================*/
create table USER
(
   USERNAME             varchar(100) not null,
   PASSWORD             varchar(100) not null,
   TOKEN                varchar(100) not null,
   USER                 varchar(100) not null,
   primary key (USERNAME)
);

alter table PLAYLIST add constraint FK_USER_HEEFT_PLAYLIST foreign key (USERNAME)
      references USER (USERNAME);

alter table PLAYLIST_BEVAT_TRACKS add constraint FK_PLAYLIST_BEVAT_TRACKS foreign key (PLAYLISTID)
      references PLAYLIST (PLAYLISTID);

alter table PLAYLIST_BEVAT_TRACKS add constraint FK_PLAYLIST_BEVAT_TRACKS2 foreign key (TRACKID)
      references TRACK (TRACKID);

