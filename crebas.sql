/*==============================================================*/
/* DBMS name:      Microsoft SQL Server 2008                    */
/* Created on:     18-3-2019 14:54:00                           */
/*==============================================================*/


if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('PLAYLIST') and o.name = 'FK_PLAYLIST_USER_HEEF_USER')
alter table PLAYLIST
   drop constraint FK_PLAYLIST_USER_HEEF_USER
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('PLAYLIST_BEVAT_TRACKS') and o.name = 'FK_PLAYLIST_PLAYLIST__PLAYLIST')
alter table PLAYLIST_BEVAT_TRACKS
   drop constraint FK_PLAYLIST_PLAYLIST__PLAYLIST
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('PLAYLIST_BEVAT_TRACKS') and o.name = 'FK_PLAYLIST_PLAYLIST__TRACK')
alter table PLAYLIST_BEVAT_TRACKS
   drop constraint FK_PLAYLIST_PLAYLIST__TRACK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('PLAYLIST')
            and   name  = 'USER_HEEFT_PLAYLIST_FK'
            and   indid > 0
            and   indid < 255)
   drop index PLAYLIST.USER_HEEFT_PLAYLIST_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('PLAYLIST')
            and   type = 'U')
   drop table PLAYLIST
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('PLAYLIST_BEVAT_TRACKS')
            and   name  = 'PLAYLIST_BEVAT_TRACKS2_FK'
            and   indid > 0
            and   indid < 255)
   drop index PLAYLIST_BEVAT_TRACKS.PLAYLIST_BEVAT_TRACKS2_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('PLAYLIST_BEVAT_TRACKS')
            and   name  = 'PLAYLIST_BEVAT_TRACKS_FK'
            and   indid > 0
            and   indid < 255)
   drop index PLAYLIST_BEVAT_TRACKS.PLAYLIST_BEVAT_TRACKS_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('PLAYLIST_BEVAT_TRACKS')
            and   type = 'U')
   drop table PLAYLIST_BEVAT_TRACKS
go

if exists (select 1
            from  sysobjects
           where  id = object_id('TRACK')
            and   type = 'U')
   drop table TRACK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('"USER"')
            and   type = 'U')
   drop table "USER"
go

if exists(select 1 from systypes where name='ALBUM')
   drop type ALBUM
go

if exists(select 1 from systypes where name='DESCRIPTION')
   drop type DESCRIPTION
go

if exists(select 1 from systypes where name='DURATION')
   drop type DURATION
go

if exists(select 1 from systypes where name='ID')
   drop type ID
go

if exists(select 1 from systypes where name='NAME')
   drop type NAME
go

if exists(select 1 from systypes where name='OFFLINEAVAILABLE')
   drop type OFFLINEAVAILABLE
go

if exists(select 1 from systypes where name='PASSWORD')
   drop type PASSWORD
go

if exists(select 1 from systypes where name='PLAYCOUNT')
   drop type PLAYCOUNT
go

if exists(select 1 from systypes where name='PUBLICATIONDATE')
   drop type PUBLICATIONDATE
go

if exists(select 1 from systypes where name='TITLE')
   drop type TITLE
go

if exists(select 1 from systypes where name='TOKEN')
   drop type TOKEN
go

if exists(select 1 from systypes where name='USERNAME')
   drop type USERNAME
go

/*==============================================================*/
/* Domain: ALBUM                                                */
/*==============================================================*/
create type ALBUM
   from varchar(100)
go

/*==============================================================*/
/* Domain: DESCRIPTION                                          */
/*==============================================================*/
create type DESCRIPTION
   from text
go

/*==============================================================*/
/* Domain: DURATION                                             */
/*==============================================================*/
create type DURATION
   from numeric
go

/*==============================================================*/
/* Domain: ID                                                   */
/*==============================================================*/
create type ID
   from numeric
go

/*==============================================================*/
/* Domain: NAME                                                 */
/*==============================================================*/
create type NAME
   from varchar(100)
go

/*==============================================================*/
/* Domain: OFFLINEAVAILABLE                                     */
/*==============================================================*/
create type OFFLINEAVAILABLE
   from bit
go

/*==============================================================*/
/* Domain: PASSWORD                                             */
/*==============================================================*/
create type PASSWORD
   from varchar(100)
go

/*==============================================================*/
/* Domain: PLAYCOUNT                                            */
/*==============================================================*/
create type PLAYCOUNT
   from numeric
go

/*==============================================================*/
/* Domain: PUBLICATIONDATE                                      */
/*==============================================================*/
create type PUBLICATIONDATE
   from datetime
go

/*==============================================================*/
/* Domain: TITLE                                                */
/*==============================================================*/
create type TITLE
   from varchar(100)
go

/*==============================================================*/
/* Domain: TOKEN                                                */
/*==============================================================*/
create type TOKEN
   from varchar(100)
go

/*==============================================================*/
/* Domain: USERNAME                                             */
/*==============================================================*/
create type USERNAME
   from varchar(100)
go

/*==============================================================*/
/* Table: PLAYLIST                                              */
/*==============================================================*/
create table PLAYLIST (
   NAME                 NAME                 not null,
   PLAYLISTID           ID                   not null,
   USERNAME             USERNAME             not null,
   constraint PK_PLAYLIST primary key nonclustered (PLAYLISTID)
)
go

/*==============================================================*/
/* Index: USER_HEEFT_PLAYLIST_FK                                */
/*==============================================================*/
create index USER_HEEFT_PLAYLIST_FK on PLAYLIST (
USERNAME ASC
)
go

/*==============================================================*/
/* Table: PLAYLIST_BEVAT_TRACKS                                 */
/*==============================================================*/
create table PLAYLIST_BEVAT_TRACKS (
   PLAYLISTID           ID                   not null,
   TRACKID              ID                   not null,
   constraint PK_PLAYLIST_BEVAT_TRACKS primary key nonclustered (PLAYLISTID, TRACKID)
)
go

/*==============================================================*/
/* Index: PLAYLIST_BEVAT_TRACKS_FK                              */
/*==============================================================*/
create index PLAYLIST_BEVAT_TRACKS_FK on PLAYLIST_BEVAT_TRACKS (
PLAYLISTID ASC
)
go

/*==============================================================*/
/* Index: PLAYLIST_BEVAT_TRACKS2_FK                             */
/*==============================================================*/
create index PLAYLIST_BEVAT_TRACKS2_FK on PLAYLIST_BEVAT_TRACKS (
TRACKID ASC
)
go

/*==============================================================*/
/* Table: TRACK                                                 */
/*==============================================================*/
create table TRACK (
   TRACKID              ID                   not null,
   TITLE                NAME                 not null,
   PERFORMER            NAME                 not null,
   DURATION             DURATION             not null,
   ALBUM                ALBUM                not null,
   PLAYCOUNT            PLAYCOUNT            null,
   PUBLICATIONDATE      PUBLICATIONDATE      null,
   DESCRIPTION          DESCRIPTION          null,
   OFFLINEAVAILABLE     OFFLINEAVAILABLE     null,
   constraint PK_TRACK primary key nonclustered (TRACKID)
)
go

/*==============================================================*/
/* Table: "USER"                                                */
/*==============================================================*/
create table "USER" (
   USERNAME             USERNAME             not null,
   PASSWORD             PASSWORD             null,
   TOKEN                TOKEN                null,
   "USER"               NAME                 null,
   constraint PK_USER primary key nonclustered (USERNAME)
)
go

alter table PLAYLIST
   add constraint FK_PLAYLIST_USER_HEEF_USER foreign key (USERNAME)
      references "USER" (USERNAME)
go

alter table PLAYLIST_BEVAT_TRACKS
   add constraint FK_PLAYLIST_PLAYLIST__PLAYLIST foreign key (PLAYLISTID)
      references PLAYLIST (PLAYLISTID)
go

alter table PLAYLIST_BEVAT_TRACKS
   add constraint FK_PLAYLIST_PLAYLIST__TRACK foreign key (TRACKID)
      references TRACK (TRACKID)
go

