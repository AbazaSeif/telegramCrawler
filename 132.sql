/*
SQLyog Professional v12.5.0 (64 bit)
MySQL - 5.5.42 : Database - adventure_db
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`adventure_db` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `adventure_db`;

/*Table structure for table `bot_ad` */

DROP TABLE IF EXISTS `bot_ad`;

CREATE TABLE `bot_ad` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `messageId` bigint(20) DEFAULT NULL,
  `fromChatId` bigint(20) DEFAULT NULL,
  `screenshotMessageId` bigint(20) DEFAULT NULL,
  `name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `viewLimit` int(11) DEFAULT NULL,
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `repFileId` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin,
  `repType` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `repText` varchar(5000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `screenshotFromChatId` bigint(20) DEFAULT NULL,
  `screenshotFileId` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `screenshotText` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin,
  `observerChatId` bigint(20) DEFAULT NULL,
  `observerMessageId` bigint(20) DEFAULT NULL,
  `url` varchar(1000) DEFAULT NULL,
  `originalMessageId` bigint(20) DEFAULT NULL,
  `originalChatId` bigint(20) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `adType` varchar(20) NOT NULL DEFAULT 'BASIC',
  `bannerFileId` varchar(200) DEFAULT NULL,
  `bannerText` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin,
  `basePrice` bigint(20) DEFAULT NULL,
  `panelAdId` bigint(20) DEFAULT NULL,
  `bannerType` varchar(20) DEFAULT NULL,
  `finalizeDate` datetime DEFAULT NULL,
  `panelOrganizationId` bigint(20) DEFAULT NULL,
  `paymentAccount` varchar(20) DEFAULT NULL,
  `privateBanner` tinyint(1) DEFAULT '0',
  `paymentType` varchar(20) DEFAULT 'VIEW',
  `campaignId` bigint(20) DEFAULT NULL,
  `buttonCaption` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `adminChatId` bigint(20) DEFAULT NULL,
  `installBasePrice` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1392 DEFAULT CHARSET=utf8;

/*Table structure for table `bot_botState` */

DROP TABLE IF EXISTS `bot_botState`;

CREATE TABLE `bot_botState` (
  `chatId` bigint(20) NOT NULL,
  `botName` varchar(20) NOT NULL,
  `state` int(11) DEFAULT NULL,
  PRIMARY KEY (`chatId`,`botName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bot_category` */

DROP TABLE IF EXISTS `bot_category`;

CREATE TABLE `bot_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Table structure for table `bot_channelGroup` */

DROP TABLE IF EXISTS `bot_channelGroup`;

CREATE TABLE `bot_channelGroup` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `channelId` bigint(20) NOT NULL,
  `groupId` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `bot_group` */

DROP TABLE IF EXISTS `bot_group`;

CREATE TABLE `bot_group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `bot_publisherChannel` */

DROP TABLE IF EXISTS `bot_publisherChannel`;

CREATE TABLE `bot_publisherChannel` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `channelUsername` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `publisherChatId` bigint(20) DEFAULT NULL,
  `categoryId` bigint(20) DEFAULT NULL,
  `channelName` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `status` varchar(20) DEFAULT 'REQUESTED',
  `view24` bigint(20) DEFAULT NULL,
  `members` bigint(20) DEFAULT NULL,
  `type` varchar(20) DEFAULT 'NORMAL',
  `joinLink` varchar(300) DEFAULT NULL,
  `chatId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1588 DEFAULT CHARSET=utf8;

/*Table structure for table `bot_rep` */

DROP TABLE IF EXISTS `bot_rep`;

CREATE TABLE `bot_rep` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `adId` bigint(20) NOT NULL,
  `publisherChatId` bigint(20) NOT NULL,
  `messageId` bigint(20) DEFAULT NULL,
  `fromChatId` bigint(20) DEFAULT NULL,
  `observerChatId` bigint(20) DEFAULT NULL,
  `observerMessageId` bigint(20) DEFAULT NULL,
  `publisherChannelId` bigint(20) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `screenshotFileId` varchar(200) DEFAULT NULL,
  `finalViewCount` bigint(20) DEFAULT NULL,
  `paymentStatus` varchar(30) DEFAULT NULL,
  `paymentType` varchar(30) DEFAULT NULL,
  `price` bigint(20) DEFAULT NULL,
  `datapointId` bigint(20) DEFAULT NULL,
  `finalClickCount` bigint(20) DEFAULT NULL,
  `traceCode` varchar(100) DEFAULT NULL,
  `basePrice` bigint(20) DEFAULT NULL,
  `payDate` datetime DEFAULT NULL,
  `viewCount` bigint(20) DEFAULT NULL,
  `postLink` varchar(300) DEFAULT NULL,
  `fileId` varchar(200) DEFAULT NULL,
  `fileName` varchar(200) DEFAULT NULL,
  `conversionValue` bigint(20) DEFAULT NULL,
  `installCount` bigint(20) DEFAULT NULL,
  `subCount` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `datapointId` (`datapointId`),
  KEY `adId` (`adId`)
) ENGINE=InnoDB AUTO_INCREMENT=182231 DEFAULT CHARSET=utf8;

/*Table structure for table `bot_screenShot` */

DROP TABLE IF EXISTS `bot_screenShot`;

CREATE TABLE `bot_screenShot` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `repId` bigint(20) DEFAULT NULL,
  `content` longblob,
  PRIMARY KEY (`id`),
  KEY `repId` (`repId`)
) ENGINE=InnoDB AUTO_INCREMENT=50977 DEFAULT CHARSET=latin1;

/*Table structure for table `bot_session` */

DROP TABLE IF EXISTS `bot_session`;

CREATE TABLE `bot_session` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `chatId` bigint(20) DEFAULT NULL,
  `name` varchar(64) DEFAULT NULL,
  `value` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `created` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5697 DEFAULT CHARSET=utf8;

/*Table structure for table `bot_user` */

DROP TABLE IF EXISTS `bot_user`;

CREATE TABLE `bot_user` (
  `chatId` bigint(20) NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `username` varchar(100) DEFAULT NULL,
  `firstname` varbinary(1000) DEFAULT NULL,
  `lastname` varbinary(300) DEFAULT NULL,
  `type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT 'USER',
  `panNumber` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `ibanNumber` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `stickerFileId` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `permissions` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`chatId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Table structure for table `ibot_ad` */

DROP TABLE IF EXISTS `ibot_ad`;

CREATE TABLE `ibot_ad` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `viewLimit` int(11) DEFAULT NULL,
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `bannerMessageId` bigint(200) DEFAULT NULL,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin,
  `url` varchar(1000) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `adType` varchar(20) NOT NULL DEFAULT 'BASIC',
  `specialCode` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `bannerFileId` varchar(200) DEFAULT NULL,
  `caption` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin,
  `bannerType` varchar(20) DEFAULT NULL,
  `finalizeDate` datetime DEFAULT NULL,
  `panelOrganizationId` bigint(20) DEFAULT NULL,
  `paymentAccount` varchar(20) DEFAULT NULL,
  `basePrice` bigint(20) DEFAULT NULL,
  `baseViewLimit` bigint(20) DEFAULT NULL,
  `bannerChannel` varchar(50) DEFAULT NULL,
  `needShortLink` tinyint(1) DEFAULT NULL,
  `keywords` text,
  `captionMessageId` bigint(20) DEFAULT NULL,
  `publishTimeLimit` datetime DEFAULT NULL,
  `campaignId` bigint(20) DEFAULT NULL,
  `mentions` varchar(500) DEFAULT NULL,
  `adminChatId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=898 DEFAULT CHARSET=utf8;

/*Table structure for table `ibot_botState` */

DROP TABLE IF EXISTS `ibot_botState`;

CREATE TABLE `ibot_botState` (
  `chatId` bigint(20) NOT NULL,
  `botName` varchar(20) NOT NULL,
  `state` int(11) DEFAULT NULL,
  PRIMARY KEY (`chatId`,`botName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `ibot_campaign` */

DROP TABLE IF EXISTS `ibot_campaign`;

CREATE TABLE `ibot_campaign` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(256) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

/*Table structure for table `ibot_category` */

DROP TABLE IF EXISTS `ibot_category`;

CREATE TABLE `ibot_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Table structure for table `ibot_rep` */

DROP TABLE IF EXISTS `ibot_rep`;

CREATE TABLE `ibot_rep` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `adId` bigint(20) NOT NULL,
  `pageId` bigint(20) NOT NULL,
  `postId` bigint(20) DEFAULT NULL,
  `postLink` varchar(500) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `screenshotFileId` varchar(200) DEFAULT NULL,
  `finalViewCount` bigint(20) DEFAULT NULL,
  `paymentStatus` varchar(30) DEFAULT NULL,
  `price` bigint(20) DEFAULT NULL,
  `datapointId` bigint(20) DEFAULT NULL,
  `finalClickCount` bigint(20) DEFAULT NULL,
  `traceCode` varchar(100) DEFAULT NULL,
  `basePrice` bigint(20) DEFAULT NULL,
  `payDate` datetime DEFAULT NULL,
  `viewCount` bigint(20) DEFAULT NULL,
  `messageId` bigint(20) DEFAULT NULL,
  `chatId` bigint(20) DEFAULT NULL,
  `publisherChatId` bigint(20) DEFAULT NULL,
  `viewLimit` bigint(20) DEFAULT NULL,
  `screenShotType` varchar(20) DEFAULT 'PHOTO',
  `storyId` bigint(20) DEFAULT NULL,
  `doneDate` datetime DEFAULT NULL,
  `publishDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `datapointId` (`datapointId`),
  KEY `adId` (`adId`),
  KEY `indexPageId` (`pageId`),
  KEY `indexPostId` (`postId`)
) ENGINE=InnoDB AUTO_INCREMENT=5765 DEFAULT CHARSET=utf8;

/*Table structure for table `ibot_screenShot` */

DROP TABLE IF EXISTS `ibot_screenShot`;

CREATE TABLE `ibot_screenShot` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `repId` bigint(20) DEFAULT NULL,
  `content` longblob,
  PRIMARY KEY (`id`),
  KEY `repId` (`repId`)
) ENGINE=InnoDB AUTO_INCREMENT=2781 DEFAULT CHARSET=utf8;

/*Table structure for table `ibot_session` */

DROP TABLE IF EXISTS `ibot_session`;

CREATE TABLE `ibot_session` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `chatId` bigint(20) DEFAULT NULL,
  `name` varchar(64) DEFAULT NULL,
  `value` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `created` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1499 DEFAULT CHARSET=utf8;

/*Table structure for table `ibot_user` */

DROP TABLE IF EXISTS `ibot_user`;

CREATE TABLE `ibot_user` (
  `chatId` bigint(20) NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `username` varchar(100) DEFAULT NULL,
  `firstname` varbinary(1000) DEFAULT NULL,
  `lastname` varbinary(300) DEFAULT NULL,
  `type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT 'USER',
  `panNumber` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `ibanNumber` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `permissions` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`chatId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Table structure for table `iobs_comment` */

DROP TABLE IF EXISTS `iobs_comment`;

CREATE TABLE `iobs_comment` (
  `pk` varchar(64) NOT NULL,
  `postId` bigint(20) DEFAULT NULL,
  `username` varchar(200) DEFAULT NULL,
  `text` varchar(2200) CHARACTER SET utf8mb4 DEFAULT NULL,
  `comments` bigint(20) DEFAULT NULL,
  `likes` bigint(20) DEFAULT NULL,
  `created` timestamp NULL DEFAULT NULL,
  `hasHashtag` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`pk`),
  KEY `postId` (`postId`),
  KEY `created` (`created`),
  KEY `likes` (`likes`),
  KEY `comments` (`comments`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `iobs_hashtag` */

DROP TABLE IF EXISTS `iobs_hashtag`;

CREATE TABLE `iobs_hashtag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(1200) CHARACTER SET utf8mb4 DEFAULT NULL,
  `status` varchar(64) DEFAULT NULL,
  `created` timestamp NULL DEFAULT NULL,
  `updated` timestamp NULL DEFAULT NULL,
  `profilePic` varchar(1200) DEFAULT NULL,
  `postNo` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Table structure for table `iobs_hashtagpost` */

DROP TABLE IF EXISTS `iobs_hashtagpost`;

CREATE TABLE `iobs_hashtagpost` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `hashtagId` bigint(20) DEFAULT NULL,
  `postId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `hashtagId` (`hashtagId`),
  KEY `postId` (`postId`),
  KEY `hashtagId_2` (`hashtagId`,`postId`)
) ENGINE=InnoDB AUTO_INCREMENT=135232 DEFAULT CHARSET=utf8;

/*Table structure for table `iobs_page` */

DROP TABLE IF EXISTS `iobs_page`;

CREATE TABLE `iobs_page` (
  `pageId` bigint(20) DEFAULT NULL,
  `name` varchar(256) DEFAULT NULL,
  `username` varchar(64) DEFAULT NULL,
  `userChatId` bigint(20) DEFAULT NULL,
  `status` varchar(32) DEFAULT NULL,
  `categoryId` bigint(20) DEFAULT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` varchar(20) DEFAULT NULL,
  `profilePic` varchar(1000) DEFAULT NULL,
  `following` bigint(20) DEFAULT NULL,
  `followers` bigint(20) DEFAULT NULL,
  `posts` bigint(20) DEFAULT NULL,
  `meanLikeCount` float DEFAULT NULL,
  `meanCommentCount` float DEFAULT NULL,
  `meanViewCount` float DEFAULT NULL,
  `viewEngagement` float DEFAULT NULL,
  `engagement` float DEFAULT NULL,
  `realFollowerCount` float DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `pageId` (`pageId`)
) ENGINE=InnoDB AUTO_INCREMENT=30265 DEFAULT CHARSET=utf8mb4;

/*Table structure for table `iobs_post` */

DROP TABLE IF EXISTS `iobs_post`;

CREATE TABLE `iobs_post` (
  `postId` bigint(20) DEFAULT NULL,
  `shortcode` varchar(256) DEFAULT NULL,
  `video` tinyint(1) DEFAULT NULL,
  `videoViews` bigint(20) DEFAULT NULL,
  `likes` bigint(20) DEFAULT NULL,
  `comments` bigint(20) DEFAULT NULL,
  `pageId` bigint(20) DEFAULT NULL,
  `createDate` timestamp NULL DEFAULT NULL,
  `caption` varchar(3000) DEFAULT NULL,
  `deleteDate` timestamp NULL DEFAULT NULL,
  `status` varchar(32) DEFAULT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `videoUrl` varchar(1200) DEFAULT NULL,
  `displayUrl` varchar(1200) DEFAULT NULL,
  `hasHashtag` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `createDate` (`createDate`),
  KEY `likes` (`likes`),
  KEY `pageId` (`pageId`),
  KEY `videoViews` (`videoViews`),
  KEY `comments` (`comments`),
  KEY `postId` (`postId`)
) ENGINE=InnoDB AUTO_INCREMENT=137597 DEFAULT CHARSET=utf8mb4;

/*Table structure for table `iobs_postView` */

DROP TABLE IF EXISTS `iobs_postView`;

CREATE TABLE `iobs_postView` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `postId` bigint(20) DEFAULT NULL,
  `time` timestamp NULL DEFAULT NULL,
  `views` bigint(20) DEFAULT NULL,
  `likes` bigint(20) DEFAULT NULL,
  `comments` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `postId` (`postId`),
  KEY `time` (`time`)
) ENGINE=InnoDB AUTO_INCREMENT=1777634761022597292 DEFAULT CHARSET=utf8mb4;

/*Table structure for table `iobs_story` */

DROP TABLE IF EXISTS `iobs_story`;

CREATE TABLE `iobs_story` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pageId` bigint(20) DEFAULT NULL,
  `link` varchar(1200) DEFAULT NULL,
  `createDate` timestamp NULL DEFAULT NULL,
  `status` varchar(32) DEFAULT NULL,
  `finalDate` timestamp NULL DEFAULT NULL,
  `mentions` varchar(1200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1085 DEFAULT CHARSET=utf8;

/*Table structure for table `iobs_storyItem` */

DROP TABLE IF EXISTS `iobs_storyItem`;

CREATE TABLE `iobs_storyItem` (
  `id` varchar(64) NOT NULL,
  `storyId` bigint(20) NOT NULL,
  `takenDate` timestamp NULL DEFAULT NULL,
  `expiredDate` timestamp NULL DEFAULT NULL,
  `imageUrl` varchar(1200) DEFAULT NULL,
  `videoUrl` varchar(1200) DEFAULT NULL,
  `webUri` varchar(1200) DEFAULT NULL,
  `createDate` timestamp NULL DEFAULT NULL,
  `deleteDate` timestamp NULL DEFAULT NULL,
  `mentions` varchar(1200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `isum_report` */

DROP TABLE IF EXISTS `isum_report`;

CREATE TABLE `isum_report` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `adId` bigint(20) DEFAULT NULL,
  `status` varchar(32) DEFAULT NULL,
  `created` timestamp NULL DEFAULT NULL,
  `endDate` timestamp NULL DEFAULT NULL,
  `commentWordCloud` longblob,
  `captionWordCloud` longblob,
  `readComments` bigint(20) DEFAULT NULL,
  `totalMentions` bigint(20) DEFAULT NULL,
  `uniqueMentions` bigint(20) DEFAULT NULL,
  `posts` bigint(20) DEFAULT NULL,
  `postsIds` varchar(10000) DEFAULT NULL,
  `campaignId` bigint(20) DEFAULT NULL,
  `hashtagId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=192 DEFAULT CHARSET=utf8;

/*Table structure for table `isum_wordFrequency` */

DROP TABLE IF EXISTS `isum_wordFrequency`;

CREATE TABLE `isum_wordFrequency` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `reportId` bigint(20) DEFAULT NULL,
  `postId` bigint(20) DEFAULT NULL,
  `word` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `frequency` bigint(20) DEFAULT NULL,
  `type` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18907 DEFAULT CHARSET=latin1;

/*Table structure for table `obs_DifferencesData` */

DROP TABLE IF EXISTS `obs_DifferencesData`;

CREATE TABLE `obs_DifferencesData` (
  `botId` int(11) NOT NULL,
  `pts` int(11) NOT NULL,
  `date` int(11) NOT NULL,
  `seq` int(11) NOT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `observerId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1659 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `obs_accessToken` */

DROP TABLE IF EXISTS `obs_accessToken`;

CREATE TABLE `obs_accessToken` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `accessHash` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Table structure for table `obs_channel` */

DROP TABLE IF EXISTS `obs_channel`;

CREATE TABLE `obs_channel` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(512) CHARACTER SET utf8 DEFAULT NULL,
  `channelId` bigint(20) DEFAULT NULL,
  `accessHash` bigint(20) DEFAULT NULL,
  `observerId` bigint(20) DEFAULT NULL,
  `flags` bigint(20) DEFAULT NULL,
  `history` tinyint(1) DEFAULT NULL,
  `updatedHistory` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `channelId` (`channelId`),
  KEY `username` (`username`(255))
) ENGINE=InnoDB AUTO_INCREMENT=820 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `obs_channelInfo` */

DROP TABLE IF EXISTS `obs_channelInfo`;

CREATE TABLE `obs_channelInfo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `channelId` bigint(20) DEFAULT NULL,
  `members` bigint(20) DEFAULT NULL,
  `time` date NOT NULL,
  `view24` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `channelId` (`channelId`),
  KEY `time` (`time`)
) ENGINE=InnoDB AUTO_INCREMENT=167370 DEFAULT CHARSET=utf8;

/*Table structure for table `obs_chat` */

DROP TABLE IF EXISTS `obs_chat`;

CREATE TABLE `obs_chat` (
  `id` bigint(20) NOT NULL,
  `isChannel` tinyint(1) NOT NULL DEFAULT '0',
  `accessHash` bigint(20) DEFAULT NULL,
  `observerId` bigint(20) NOT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `title` varchar(512) DEFAULT NULL,
  `members` bigint(20) DEFAULT NULL,
  `updated` timestamp NULL DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT NULL,
  `updateView24` timestamp NULL DEFAULT '2018-04-05 10:07:47',
  `view24` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`,`observerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Table structure for table `obs_importChannel` */

DROP TABLE IF EXISTS `obs_importChannel`;

CREATE TABLE `obs_importChannel` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `observerId` bigint(20) DEFAULT NULL,
  `chatId` bigint(20) DEFAULT NULL,
  `maxHistoryLenght` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `chatId` (`chatId`),
  CONSTRAINT `importChannel_ibfk_1` FOREIGN KEY (`chatId`) REFERENCES `obs_chat` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `obs_message` */

DROP TABLE IF EXISTS `obs_message`;

CREATE TABLE `obs_message` (
  `toId` bigint(20) NOT NULL,
  `messageId` bigint(20) NOT NULL,
  `fromId` bigint(20) DEFAULT NULL,
  `fwdFromId` bigint(20) DEFAULT NULL,
  `fwdDate` timestamp NULL DEFAULT NULL,
  `fwdChannelId` bigint(20) DEFAULT NULL,
  `fwdChannelPost` bigint(20) DEFAULT NULL,
  `message` varchar(10000) DEFAULT NULL,
  `views` bigint(20) DEFAULT NULL,
  `date` timestamp NULL DEFAULT NULL,
  `observerId` bigint(20) NOT NULL,
  `deleted` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`toId`,`messageId`,`observerId`),
  KEY `toId` (`toId`),
  KEY `messageId` (`messageId`),
  KEY `toId_2` (`toId`,`messageId`),
  KEY `fwdChannelPost` (`fwdChannelPost`),
  KEY `fwdChannelId` (`fwdChannelId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Table structure for table `obs_messageText` */

DROP TABLE IF EXISTS `obs_messageText`;

CREATE TABLE `obs_messageText` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `toId` bigint(20) DEFAULT NULL,
  `messageId` bigint(20) DEFAULT NULL,
  `observerId` bigint(20) DEFAULT NULL,
  `messageText` varchar(6000) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=244862 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Table structure for table `obs_messageView` */

DROP TABLE IF EXISTS `obs_messageView`;

CREATE TABLE `obs_messageView` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `views` bigint(20) unsigned DEFAULT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `messageId` bigint(20) DEFAULT NULL,
  `toId` bigint(20) DEFAULT NULL,
  `observerId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `toId` (`toId`),
  KEY `messageId` (`messageId`),
  KEY `messageId_2` (`messageId`,`toId`),
  KEY `time` (`time`),
  KEY `observerId` (`observerId`),
  KEY `messageId_3` (`messageId`,`toId`)
) ENGINE=InnoDB AUTO_INCREMENT=12357175 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `obs_observer` */

DROP TABLE IF EXISTS `obs_observer`;

CREATE TABLE `obs_observer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `phoneNumber` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `code` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `apiId` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `apiHash` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `enabled` tinyint(1) DEFAULT NULL,
  `readyToLoad` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `obs_users` */

DROP TABLE IF EXISTS `obs_users`;

CREATE TABLE `obs_users` (
  `userId` int(11) NOT NULL,
  `userHash` bigint(20) DEFAULT NULL,
  `observerId` bigint(20) NOT NULL,
  PRIMARY KEY (`userId`,`observerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `panel_ad` */

DROP TABLE IF EXISTS `panel_ad`;

CREATE TABLE `panel_ad` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `campaignId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `panel_campaign` */

DROP TABLE IF EXISTS `panel_campaign`;

CREATE TABLE `panel_campaign` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `description` varchar(512) COLLATE utf8_bin DEFAULT NULL,
  `orgId` bigint(20) DEFAULT NULL,
  `status` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `created` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `panel_channelGroup` */

DROP TABLE IF EXISTS `panel_channelGroup`;

CREATE TABLE `panel_channelGroup` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `basePrice` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Table structure for table `panel_order` */

DROP TABLE IF EXISTS `panel_order`;

CREATE TABLE `panel_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  `bannerLink` varchar(200) COLLATE utf8mb4_bin DEFAULT NULL,
  `bannerCaption` varchar(250) COLLATE utf8mb4_bin DEFAULT NULL,
  `viewCount` int(11) DEFAULT NULL,
  `basePrice` bigint(20) DEFAULT NULL,
  `price` bigint(20) DEFAULT NULL,
  `status` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `photo` longblob,
  `title` varchar(200) COLLATE utf8mb4_bin DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `startDate` datetime DEFAULT NULL,
  `adId` bigint(20) DEFAULT NULL,
  `description` text COLLATE utf8mb4_bin,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Table structure for table `panel_orderChannelGroup` */

DROP TABLE IF EXISTS `panel_orderChannelGroup`;

CREATE TABLE `panel_orderChannelGroup` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `orderId` bigint(20) DEFAULT NULL,
  `channelGroupId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=123 DEFAULT CHARSET=latin1;

/*Table structure for table `panel_organization` */

DROP TABLE IF EXISTS `panel_organization`;

CREATE TABLE `panel_organization` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `description` varchar(512) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=97 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `panel_payment` */

DROP TABLE IF EXISTS `panel_payment`;

CREATE TABLE `panel_payment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createDate` datetime DEFAULT NULL,
  `returnDate` datetime DEFAULT NULL,
  `finishDate` datetime DEFAULT NULL,
  `amount` bigint(20) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `username` varchar(100) DEFAULT NULL,
  `orderId` bigint(20) DEFAULT NULL,
  `rrn` varchar(64) DEFAULT NULL,
  `registerDate` datetime DEFAULT NULL,
  `authority` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `panel_permission` */

DROP TABLE IF EXISTS `panel_permission`;

CREATE TABLE `panel_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `caption` varchar(100) DEFAULT NULL,
  `valid` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=98 DEFAULT CHARSET=utf8;

/*Table structure for table `panel_role` */

DROP TABLE IF EXISTS `panel_role`;

CREATE TABLE `panel_role` (
  `role` varchar(45) NOT NULL,
  `caption` varchar(100) NOT NULL,
  `parent` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`role`),
  KEY `parent` (`parent`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `panel_rolePermission` */

DROP TABLE IF EXISTS `panel_rolePermission`;

CREATE TABLE `panel_rolePermission` (
  `role` varchar(20) NOT NULL,
  `permissionId` bigint(20) NOT NULL,
  KEY `permissionId` (`permissionId`),
  KEY `role` (`role`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `panel_user` */

DROP TABLE IF EXISTS `panel_user`;

CREATE TABLE `panel_user` (
  `username` varchar(100) NOT NULL,
  `name` varchar(100) NOT NULL,
  `password` varchar(70) DEFAULT NULL,
  `isEnable` tinyint(1) DEFAULT NULL,
  `description` varchar(512) DEFAULT NULL,
  `passwordExpired` timestamp NULL DEFAULT NULL,
  `orgId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `panel_userPermission` */

DROP TABLE IF EXISTS `panel_userPermission`;

CREATE TABLE `panel_userPermission` (
  `denied` tinyint(1) NOT NULL,
  `username` varchar(100) NOT NULL,
  `permissionId` bigint(20) NOT NULL,
  KEY `username` (`username`),
  KEY `permissionId` (`permissionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `panel_userRole` */

DROP TABLE IF EXISTS `panel_userRole`;

CREATE TABLE `panel_userRole` (
  `role` varchar(45) NOT NULL,
  `username` varchar(100) NOT NULL,
  PRIMARY KEY (`role`,`username`),
  KEY `username` (`username`),
  KEY `role` (`role`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `shorten_click` */

DROP TABLE IF EXISTS `shorten_click`;

CREATE TABLE `shorten_click` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `datapointId` bigint(20) DEFAULT NULL,
  `ip` varchar(40) DEFAULT NULL,
  `agentId` bigint(20) DEFAULT NULL,
  `cookie` varchar(40) DEFAULT NULL,
  `headers` varchar(1000) DEFAULT NULL,
  `processed` int(11) DEFAULT '0',
  `created` timestamp NULL DEFAULT NULL,
  `iCookie` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `datapointId` (`datapointId`),
  KEY `cookie` (`cookie`),
  KEY `processed` (`processed`)
) ENGINE=InnoDB AUTO_INCREMENT=69666 DEFAULT CHARSET=utf8;

/*Table structure for table `shorten_datapoint` */

DROP TABLE IF EXISTS `shorten_datapoint`;

CREATE TABLE `shorten_datapoint` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uri` varchar(1024) DEFAULT NULL,
  `name` varchar(512) DEFAULT NULL,
  `title` varchar(512) DEFAULT NULL,
  `created` datetime NOT NULL,
  `loaded` varchar(24) DEFAULT NULL,
  `domainId` bigint(20) DEFAULT NULL,
  `redirectType` bigint(20) DEFAULT NULL,
  `url` varchar(1024) DEFAULT NULL,
  `groupId` bigint(20) DEFAULT NULL,
  `totalClicks` bigint(20) DEFAULT NULL,
  `uniqueClicks` bigint(20) DEFAULT NULL,
  `status` varchar(24) DEFAULT NULL,
  `botClicks` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ipAddress` (`uri`(255)),
  KEY `name` (`name`(255))
) ENGINE=InnoDB AUTO_INCREMENT=326395859 DEFAULT CHARSET=utf8;

/*Table structure for table `shorten_datapointStat` */

DROP TABLE IF EXISTS `shorten_datapointStat`;

CREATE TABLE `shorten_datapointStat` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `uniqueClicks` bigint(20) DEFAULT NULL,
  `totalClicks` bigint(20) DEFAULT NULL,
  `datapointId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `time` (`time`),
  KEY `datapointId` (`datapointId`)
) ENGINE=InnoDB AUTO_INCREMENT=643776 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Table structure for table `shorten_event` */

DROP TABLE IF EXISTS `shorten_event`;

CREATE TABLE `shorten_event` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ip` varchar(64) CHARACTER SET utf8 DEFAULT NULL,
  `ci` varchar(64) CHARACTER SET utf8 DEFAULT NULL,
  `ty` varchar(64) CHARACTER SET utf8 DEFAULT NULL,
  `type` varchar(64) CHARACTER SET utf8 DEFAULT NULL,
  `eventId` varchar(64) CHARACTER SET utf8 DEFAULT NULL,
  `reason` varchar(512) CHARACTER SET utf8 DEFAULT NULL,
  `time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `token` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10078 DEFAULT CHARSET=latin1;

/*Table structure for table `shorten_hitAccess` */

DROP TABLE IF EXISTS `shorten_hitAccess`;

CREATE TABLE `shorten_hitAccess` (
  `id` varchar(64) NOT NULL,
  `osFamilyName` varchar(64) DEFAULT NULL,
  `osName` varchar(64) DEFAULT NULL,
  `browserFamilyName` varchar(64) DEFAULT NULL,
  `browserType` varchar(64) DEFAULT NULL,
  `ip` varchar(32) DEFAULT NULL,
  `city` varchar(64) DEFAULT NULL,
  `country` varchar(16) DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `region` varchar(200) DEFAULT NULL,
  `organization` varchar(64) DEFAULT NULL,
  `datapointId` bigint(20) DEFAULT NULL,
  `accessTime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `shorten_keyValue` */

DROP TABLE IF EXISTS `shorten_keyValue`;

CREATE TABLE `shorten_keyValue` (
  `id` bigint(20) NOT NULL,
  `kkey` varchar(128) NOT NULL,
  `vvalue` varchar(128) NOT NULL,
  `cnt` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`,`kkey`,`vvalue`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `sum_repPosition` */

DROP TABLE IF EXISTS `sum_repPosition`;

CREATE TABLE `sum_repPosition` (
  `id` bigint(20) NOT NULL,
  `duration` bigint(20) DEFAULT NULL,
  `position` float DEFAULT NULL,
  `fraud` tinyint(1) DEFAULT NULL,
  `fraudType` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Table structure for table `sum_repPostIndex` */

DROP TABLE IF EXISTS `sum_repPostIndex`;

CREATE TABLE `sum_repPostIndex` (
  `repId` bigint(20) DEFAULT NULL,
  `x` timestamp NULL DEFAULT NULL,
  `y` timestamp NULL DEFAULT NULL,
  `postIndex` bigint(20) DEFAULT NULL,
  `bannerAlive` tinyint(1) DEFAULT NULL,
  `bannerX` timestamp NULL DEFAULT NULL,
  `bannerY` timestamp NULL DEFAULT NULL,
  KEY `repId` (`repId`,`x`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Table structure for table `test` */

DROP TABLE IF EXISTS `test`;

CREATE TABLE `test` (
  `str` varchar(32) DEFAULT NULL,
  `ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `tmp_vipChannel` */

DROP TABLE IF EXISTS `tmp_vipChannel`;

CREATE TABLE `tmp_vipChannel` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `channelName` varchar(200) COLLATE utf8mb4_bin DEFAULT NULL,
  `channelUsername` varchar(200) COLLATE utf8mb4_bin DEFAULT NULL,
  `viewPerDay` bigint(20) DEFAULT NULL,
  `price` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Table structure for table `versions` */

DROP TABLE IF EXISTS `versions`;

CREATE TABLE `versions` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Version` int(11) DEFAULT NULL,
  `observerId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `bot_organizationFinancial` */

DROP TABLE IF EXISTS `bot_organizationFinancial`;

/*!50001 DROP VIEW IF EXISTS `bot_organizationFinancial` */;
/*!50001 DROP TABLE IF EXISTS `bot_organizationFinancial` */;

/*!50001 CREATE TABLE  `bot_organizationFinancial`(
 `id` bigint(20) ,
 `name` varchar(256) ,
 `campaignName` varchar(200) ,
 `bannerInfo` varchar(20) ,
 `description` text ,
 `orderDate` varchar(1) ,
 `createDate` datetime ,
 `finalizeDate` datetime ,
 `TYPE` varchar(8) ,
 `cost` decimal(65,4) ,
 `count` decimal(41,0) 
)*/;

/*Table structure for table `ibot_organizationFinancial` */

DROP TABLE IF EXISTS `ibot_organizationFinancial`;

/*!50001 DROP VIEW IF EXISTS `ibot_organizationFinancial` */;
/*!50001 DROP TABLE IF EXISTS `ibot_organizationFinancial` */;

/*!50001 CREATE TABLE  `ibot_organizationFinancial`(
 `id` bigint(20) ,
 `name` varchar(256) ,
 `campaignName` varchar(200) ,
 `bannerInfo` varchar(41) ,
 `description` text ,
 `orderDate` varchar(1) ,
 `createDate` datetime ,
 `finalizeDate` datetime ,
 `TYPE` varchar(9) ,
 `cost` decimal(65,4) ,
 `count` decimal(41,0) 
)*/;

/*Table structure for table `ibot_publishers` */

DROP TABLE IF EXISTS `ibot_publishers`;

/*!50001 DROP VIEW IF EXISTS `ibot_publishers` */;
/*!50001 DROP TABLE IF EXISTS `ibot_publishers` */;

/*!50001 CREATE TABLE  `ibot_publishers`(
 `id` bigint(20) ,
 `username` varchar(64) ,
 `name` varchar(256) ,
 `category` varchar(50) ,
 `following` bigint(20) ,
 `followers` bigint(20) ,
 `posts` bigint(20) ,
 `engagement` float ,
 `viewEngagement` float ,
 `meanView` float ,
 `meanLike` float ,
 `meanComment` float ,
 `realFollowerCount` float ,
 `userAddress` varchar(100) ,
 `adminName` varchar(50) ,
 `pan` varchar(17) ,
 `iban` varchar(25) ,
 `chatId` bigint(20) ,
 `pushCount` bigint(21) ,
 `doneCount` decimal(23,0) ,
 `storyDoneAveragePrice` decimal(16,0) ,
 `videoDoneAveragePrice` decimal(16,0) ,
 `photoDoneAveragePrice` decimal(16,0) ,
 `conversion` decimal(48,4) 
)*/;

/*Table structure for table `ipanel_adStats` */

DROP TABLE IF EXISTS `ipanel_adStats`;

/*!50001 DROP VIEW IF EXISTS `ipanel_adStats` */;
/*!50001 DROP TABLE IF EXISTS `ipanel_adStats` */;

/*!50001 CREATE TABLE  `ipanel_adStats`(
 `id` varchar(36) ,
 `adId` bigint(20) ,
 `postId` bigint(20) ,
 `views` decimal(16,0) ,
 `likes` decimal(16,0) ,
 `comments` decimal(16,0) ,
 `time` varchar(19) 
)*/;

/*Table structure for table `ipanel_adView` */

DROP TABLE IF EXISTS `ipanel_adView`;

/*!50001 DROP VIEW IF EXISTS `ipanel_adView` */;
/*!50001 DROP TABLE IF EXISTS `ipanel_adView` */;

/*!50001 CREATE TABLE  `ipanel_adView`(
 `adId` bigint(20) ,
 `adName` varchar(200) ,
 `adType` varchar(20) ,
 `caption` text ,
 `adStatus` varchar(20) ,
 `campaignId` bigint(20) ,
 `totalClicks` decimal(41,0) ,
 `uniqueClicks` decimal(41,0) ,
 `viewCount` decimal(41,0) ,
 `views` decimal(41,0) ,
 `likes` decimal(41,0) ,
 `comments` decimal(41,0) 
)*/;

/*Table structure for table `ipanel_campaign` */

DROP TABLE IF EXISTS `ipanel_campaign`;

/*!50001 DROP VIEW IF EXISTS `ipanel_campaign` */;
/*!50001 DROP TABLE IF EXISTS `ipanel_campaign` */;

/*!50001 CREATE TABLE  `ipanel_campaign`(
 `id` bigint(20) ,
 `name` varchar(256) ,
 `totalClicks` decimal(63,0) ,
 `uniqueClicks` decimal(63,0) ,
 `viewCount` decimal(63,0) ,
 `views` decimal(63,0) ,
 `likes` decimal(63,0) ,
 `comments` decimal(63,0) 
)*/;

/*Table structure for table `ipanel_financial` */

DROP TABLE IF EXISTS `ipanel_financial`;

/*!50001 DROP VIEW IF EXISTS `ipanel_financial` */;
/*!50001 DROP TABLE IF EXISTS `ipanel_financial` */;

/*!50001 CREATE TABLE  `ipanel_financial`(
 `adId` bigint(20) ,
 `name` varchar(200) ,
 `finalizeDate` datetime ,
 `paid` decimal(41,0) ,
 `left` decimal(64,4) ,
 `total` decimal(65,4) ,
 `notPay` decimal(64,4) ,
 `viewCount` decimal(41,0) 
)*/;

/*Table structure for table `ipanel_hashtag` */

DROP TABLE IF EXISTS `ipanel_hashtag`;

/*!50001 DROP VIEW IF EXISTS `ipanel_hashtag` */;
/*!50001 DROP TABLE IF EXISTS `ipanel_hashtag` */;

/*!50001 CREATE TABLE  `ipanel_hashtag`(
 `id` bigint(20) ,
 `name` varchar(1200) ,
 `status` varchar(64) ,
 `created` timestamp ,
 `updated` timestamp ,
 `profilePicSrc` varchar(1200) ,
 `postNo` bigint(20) ,
 `likes` decimal(41,0) ,
 `comments` decimal(41,0) ,
 `views` decimal(41,0) ,
 `posts` bigint(21) 
)*/;

/*Table structure for table `ipanel_hashtagcomment` */

DROP TABLE IF EXISTS `ipanel_hashtagcomment`;

/*!50001 DROP VIEW IF EXISTS `ipanel_hashtagcomment` */;
/*!50001 DROP TABLE IF EXISTS `ipanel_hashtagcomment` */;

/*!50001 CREATE TABLE  `ipanel_hashtagcomment`(
 `hashtagId` bigint(20) ,
 `pk` varchar(64) ,
 `postId` bigint(20) ,
 `username` varchar(200) ,
 `text` varchar(2200) ,
 `comments` bigint(20) ,
 `likes` bigint(20) ,
 `created` timestamp 
)*/;

/*Table structure for table `ipanel_hashtagpost` */

DROP TABLE IF EXISTS `ipanel_hashtagpost`;

/*!50001 DROP VIEW IF EXISTS `ipanel_hashtagpost` */;
/*!50001 DROP TABLE IF EXISTS `ipanel_hashtagpost` */;

/*!50001 CREATE TABLE  `ipanel_hashtagpost`(
 `hashtagId` bigint(20) ,
 `postId` bigint(20) ,
 `shortcode` varchar(256) ,
 `video` tinyint(1) ,
 `views` bigint(20) ,
 `likes` bigint(20) ,
 `comments` bigint(20) ,
 `pageId` bigint(20) ,
 `createDate` timestamp ,
 `caption` varchar(3000) ,
 `deleteDate` timestamp ,
 `status` varchar(32) ,
 `id` bigint(20) ,
 `displayUrl` varchar(1200) ,
 `videoUrl` varchar(1200) 
)*/;

/*Table structure for table `ipanel_hashtagpoststat` */

DROP TABLE IF EXISTS `ipanel_hashtagpoststat`;

/*!50001 DROP VIEW IF EXISTS `ipanel_hashtagpoststat` */;
/*!50001 DROP TABLE IF EXISTS `ipanel_hashtagpoststat` */;

/*!50001 CREATE TABLE  `ipanel_hashtagpoststat`(
 `id` varchar(36) ,
 `hashtagId` bigint(20) ,
 `time` varchar(10) ,
 `postNo` bigint(21) ,
 `likes` decimal(41,0) ,
 `comments` decimal(41,0) ,
 `views` decimal(41,0) 
)*/;

/*Table structure for table `ipanel_postView` */

DROP TABLE IF EXISTS `ipanel_postView`;

/*!50001 DROP VIEW IF EXISTS `ipanel_postView` */;
/*!50001 DROP TABLE IF EXISTS `ipanel_postView` */;

/*!50001 CREATE TABLE  `ipanel_postView`(
 `id` varchar(36) ,
 `postId` bigint(20) ,
 `views` decimal(16,0) ,
 `likes` decimal(16,0) ,
 `comments` decimal(16,0) ,
 `TIME` varchar(19) 
)*/;

/*Table structure for table `ipanel_repBrief` */

DROP TABLE IF EXISTS `ipanel_repBrief`;

/*!50001 DROP VIEW IF EXISTS `ipanel_repBrief` */;
/*!50001 DROP TABLE IF EXISTS `ipanel_repBrief` */;

/*!50001 CREATE TABLE  `ipanel_repBrief`(
 `pageId` bigint(20) ,
 `pushCount` bigint(21) ,
 `doneCount` decimal(23,0) ,
 `storyDoneAveragePrice` decimal(16,0) ,
 `videoDoneAveragePrice` decimal(16,0) ,
 `photoDoneAveragePrice` decimal(16,0) ,
 `conversion` decimal(48,4) 
)*/;

/*Table structure for table `ipanel_repDetail` */

DROP TABLE IF EXISTS `ipanel_repDetail`;

/*!50001 DROP VIEW IF EXISTS `ipanel_repDetail` */;
/*!50001 DROP TABLE IF EXISTS `ipanel_repDetail` */;

/*!50001 CREATE TABLE  `ipanel_repDetail`(
 `id` bigint(20) ,
 `userChatId` bigint(20) ,
 `link` text ,
 `username` varchar(100) 
)*/;

/*Table structure for table `ipanel_repView` */

DROP TABLE IF EXISTS `ipanel_repView`;

/*!50001 DROP VIEW IF EXISTS `ipanel_repView` */;
/*!50001 DROP TABLE IF EXISTS `ipanel_repView` */;

/*!50001 CREATE TABLE  `ipanel_repView`(
 `id` bigint(20) ,
 `adId` bigint(20) ,
 `campaignId` bigint(20) ,
 `pageId` bigint(20) ,
 `postId` bigint(20) ,
 `storyId` bigint(20) ,
 `postLink` varchar(500) ,
 `repStatus` varchar(20) ,
 `screenshotFileId` varchar(200) ,
 `screenshotType` varchar(20) ,
 `status` varchar(20) ,
 `viewCount` bigint(20) ,
 `totalClicks` bigint(20) ,
 `uniqueClicks` bigint(20) ,
 `pageName` varchar(256) ,
 `pageCategoryId` bigint(20) ,
 `pageProfilePic` varchar(1000) ,
 `pageFollowing` bigint(20) ,
 `pageFollowers` bigint(20) ,
 `pageEngagement` float ,
 `pageRealFollowers` float ,
 `pagePosts` bigint(20) ,
 `pageUsername` varchar(64) ,
 `shortcode` varchar(256) ,
 `video` tinyint(1) ,
 `iPostId` bigint(20) ,
 `views` bigint(20) ,
 `likes` bigint(20) ,
 `comments` bigint(20) ,
 `postCreateDate` timestamp ,
 `postDeleteDate` timestamp 
)*/;

/*Table structure for table `obs_adMessage` */

DROP TABLE IF EXISTS `obs_adMessage`;

/*!50001 DROP VIEW IF EXISTS `obs_adMessage` */;
/*!50001 DROP TABLE IF EXISTS `obs_adMessage` */;

/*!50001 CREATE TABLE  `obs_adMessage`(
 `messageId` bigint(20) ,
 `channelId` varchar(16) ,
 `adId` bigint(20) 
)*/;

/*Table structure for table `obs_banner` */

DROP TABLE IF EXISTS `obs_banner`;

/*!50001 DROP VIEW IF EXISTS `obs_banner` */;
/*!50001 DROP TABLE IF EXISTS `obs_banner` */;

/*!50001 CREATE TABLE  `obs_banner`(
 `messageId` bigint(20) ,
 `channelId` varchar(16) ,
 `adId` bigint(20) 
)*/;

/*Table structure for table `obs_rep` */

DROP TABLE IF EXISTS `obs_rep`;

/*!50001 DROP VIEW IF EXISTS `obs_rep` */;
/*!50001 DROP TABLE IF EXISTS `obs_rep` */;

/*!50001 CREATE TABLE  `obs_rep`(
 `messageId` bigint(20) ,
 `channelId` varchar(16) ,
 `adId` bigint(20) ,
 `publisherChatId` bigint(20) 
)*/;

/*Table structure for table `panel_adClickStat` */

DROP TABLE IF EXISTS `panel_adClickStat`;

/*!50001 DROP VIEW IF EXISTS `panel_adClickStat` */;
/*!50001 DROP TABLE IF EXISTS `panel_adClickStat` */;

/*!50001 CREATE TABLE  `panel_adClickStat`(
 `id` varchar(36) ,
 `adId` bigint(20) ,
 `datapointId` bigint(20) ,
 `uniqueClicks` decimal(16,0) ,
 `totalClicks` decimal(16,0) ,
 `time` varchar(19) 
)*/;

/*Table structure for table `panel_adClicks` */

DROP TABLE IF EXISTS `panel_adClicks`;

/*!50001 DROP VIEW IF EXISTS `panel_adClicks` */;
/*!50001 DROP TABLE IF EXISTS `panel_adClicks` */;

/*!50001 CREATE TABLE  `panel_adClicks`(
 `adId` bigint(20) ,
 `clicks` decimal(41,0) ,
 `totalClicks` decimal(41,0) 
)*/;

/*Table structure for table `panel_adStats` */

DROP TABLE IF EXISTS `panel_adStats`;

/*!50001 DROP VIEW IF EXISTS `panel_adStats` */;
/*!50001 DROP TABLE IF EXISTS `panel_adStats` */;

/*!50001 CREATE TABLE  `panel_adStats`(
 `id` varchar(36) ,
 `adId` bigint(20) ,
 `campaignId` bigint(20) ,
 `messageId` bigint(20) ,
 `clicks` int(1) ,
 `totalClicks` int(1) ,
 `views` decimal(16,0) ,
 `time` varchar(19) 
)*/;

/*Table structure for table `panel_adStats1` */

DROP TABLE IF EXISTS `panel_adStats1`;

/*!50001 DROP VIEW IF EXISTS `panel_adStats1` */;
/*!50001 DROP TABLE IF EXISTS `panel_adStats1` */;

/*!50001 CREATE TABLE  `panel_adStats1`(
 `id` varchar(36) ,
 `adId` bigint(20) ,
 `views` decimal(16,0) ,
 `clicks` bigint(17) ,
 `totalClicks` bigint(17) ,
 `time` varchar(19) 
)*/;

/*Table structure for table `panel_adView` */

DROP TABLE IF EXISTS `panel_adView`;

/*!50001 DROP VIEW IF EXISTS `panel_adView` */;
/*!50001 DROP TABLE IF EXISTS `panel_adView` */;

/*!50001 CREATE TABLE  `panel_adView`(
 `adStatus` varchar(20) ,
 `adName` varchar(200) ,
 `adId` bigint(20) ,
 `campaignId` bigint(20) ,
 `messageId` bigint(20) ,
 `views` bigint(20) ,
 `clicks` decimal(41,0) ,
 `totalClicks` decimal(41,0) 
)*/;

/*Table structure for table `panel_aggregateAdView` */

DROP TABLE IF EXISTS `panel_aggregateAdView`;

/*!50001 DROP VIEW IF EXISTS `panel_aggregateAdView` */;
/*!50001 DROP TABLE IF EXISTS `panel_aggregateAdView` */;

/*!50001 CREATE TABLE  `panel_aggregateAdView`(
 `campaignId` bigint(20) ,
 `messageId` bigint(20) ,
 `clicks` decimal(63,0) ,
 `totalClicks` decimal(63,0) ,
 `views` bigint(20) 
)*/;

/*Table structure for table `panel_channelDetail` */

DROP TABLE IF EXISTS `panel_channelDetail`;

/*!50001 DROP VIEW IF EXISTS `panel_channelDetail` */;
/*!50001 DROP TABLE IF EXISTS `panel_channelDetail` */;

/*!50001 CREATE TABLE  `panel_channelDetail`(
 `id` bigint(20) ,
 `categoryId` bigint(20) ,
 `categoryName` varchar(200) ,
 `channelName` varchar(200) ,
 `channelUsername` varchar(200) ,
 `members` bigint(20) ,
 `view24` bigint(20) ,
 `pushCount` bigint(21) ,
 `doneCount` decimal(23,0) ,
 `cpmDoneAveragePrice` decimal(16,0) ,
 `cpcDoneAveragePrice` decimal(16,0) ,
 `clickAvg` decimal(16,0) ,
 `conversion` decimal(48,4) 
)*/;

/*Table structure for table `panel_cpaStat` */

DROP TABLE IF EXISTS `panel_cpaStat`;

/*!50001 DROP VIEW IF EXISTS `panel_cpaStat` */;
/*!50001 DROP TABLE IF EXISTS `panel_cpaStat` */;

/*!50001 CREATE TABLE  `panel_cpaStat`(
 `channelUsername` varchar(200) ,
 `views` decimal(41,0) ,
 `costs` decimal(64,4) ,
 `installs` decimal(41,0) ,
 `incomes` decimal(60,0) ,
 `campaings` bigint(21) ,
 `maxConversion` decimal(26,2) ,
 `minConversion` decimal(26,2) 
)*/;

/*Table structure for table `panel_financial` */

DROP TABLE IF EXISTS `panel_financial`;

/*!50001 DROP VIEW IF EXISTS `panel_financial` */;
/*!50001 DROP TABLE IF EXISTS `panel_financial` */;

/*!50001 CREATE TABLE  `panel_financial`(
 `adId` bigint(20) ,
 `name` varchar(200) ,
 `finalizeDate` datetime ,
 `paid` decimal(41,0) ,
 `left` decimal(64,4) ,
 `total` decimal(65,4) ,
 `notPay` decimal(64,4) ,
 `viewCount` decimal(41,0) 
)*/;

/*Table structure for table `panel_message` */

DROP TABLE IF EXISTS `panel_message`;

/*!50001 DROP VIEW IF EXISTS `panel_message` */;
/*!50001 DROP TABLE IF EXISTS `panel_message` */;

/*!50001 CREATE TABLE  `panel_message`(
 `id` varchar(62) ,
 `toId` bigint(20) ,
 `messageId` bigint(20) ,
 `fromId` bigint(20) ,
 `fwdFromId` bigint(20) ,
 `fwdDate` timestamp ,
 `fwdChannelId` bigint(20) ,
 `fwdChannelPost` bigint(20) ,
 `message` varchar(10000) ,
 `views` bigint(20) ,
 `createDate` timestamp ,
 `observerId` bigint(20) ,
 `deleteDate` timestamp ,
 `username` varchar(255) ,
 `channelId` bigint(20) 
)*/;

/*Table structure for table `panel_repBrief` */

DROP TABLE IF EXISTS `panel_repBrief`;

/*!50001 DROP VIEW IF EXISTS `panel_repBrief` */;
/*!50001 DROP TABLE IF EXISTS `panel_repBrief` */;

/*!50001 CREATE TABLE  `panel_repBrief`(
 `publisherChannelId` bigint(20) ,
 `pushCount` bigint(21) ,
 `doneCount` decimal(23,0) ,
 `cpmDoneAveragePrice` decimal(16,0) ,
 `cpcDoneAveragePrice` decimal(16,0) ,
 `clickAvg` decimal(16,0) ,
 `conversion` decimal(48,4) 
)*/;

/*Table structure for table `panel_repView` */

DROP TABLE IF EXISTS `panel_repView`;

/*!50001 DROP VIEW IF EXISTS `panel_repView` */;
/*!50001 DROP TABLE IF EXISTS `panel_repView` */;

/*!50001 CREATE TABLE  `panel_repView`(
 `id` bigint(20) ,
 `channelUsername` varchar(200) ,
 `channelName` varchar(200) ,
 `channelId` bigint(20) ,
 `views` bigint(20) ,
 `adId` bigint(20) ,
 `viewCount` bigint(20) ,
 `postLink` varchar(300) ,
 `repStatus` varchar(20) ,
 `messageId` bigint(20) ,
 `basePrice` bigint(20) ,
 `conversionValue` bigint(20) ,
 `installCount` bigint(20) ,
 `subCount` bigint(20) ,
 `finalViewCount` bigint(20) ,
 `channelChatId` bigint(20) ,
 `fileName` varchar(200) ,
 `realChannelChatId` bigint(20) ,
 `clicks` bigint(20) ,
 `totalClicks` bigint(20) 
)*/;

/*Table structure for table `shorten_aggregatedatapointstat` */

DROP TABLE IF EXISTS `shorten_aggregatedatapointstat`;

/*!50001 DROP VIEW IF EXISTS `shorten_aggregatedatapointstat` */;
/*!50001 DROP TABLE IF EXISTS `shorten_aggregatedatapointstat` */;

/*!50001 CREATE TABLE  `shorten_aggregatedatapointstat`(
 `datapointId` bigint(20) ,
 `totalClicks` decimal(23,0) ,
 `uniqueClicks` decimal(23,0) ,
 `botClicks` decimal(23,0) 
)*/;

/*Table structure for table `shortner_adjust` */

DROP TABLE IF EXISTS `shortner_adjust`;

/*!50001 DROP VIEW IF EXISTS `shortner_adjust` */;
/*!50001 DROP TABLE IF EXISTS `shortner_adjust` */;

/*!50001 CREATE TABLE  `shortner_adjust`(
 `type` varchar(75) ,
 `total` bigint(21) ,
 `date` varchar(10) 
)*/;

/*Table structure for table `sum_banner` */

DROP TABLE IF EXISTS `sum_banner`;

/*!50001 DROP VIEW IF EXISTS `sum_banner` */;
/*!50001 DROP TABLE IF EXISTS `sum_banner` */;

/*!50001 CREATE TABLE  `sum_banner`(
 `adId` bigint(20) ,
 `channelId` varchar(16) ,
 `messageId` bigint(20) ,
 `createDate` datetime 
)*/;

/*Table structure for table `sum_channel` */

DROP TABLE IF EXISTS `sum_channel`;

/*!50001 DROP VIEW IF EXISTS `sum_channel` */;
/*!50001 DROP TABLE IF EXISTS `sum_channel` */;

/*!50001 CREATE TABLE  `sum_channel`(
 `username` varchar(512) ,
 `members` bigint(20) ,
 `view24` decimal(16,0) 
)*/;

/*Table structure for table `sum_channelInfo` */

DROP TABLE IF EXISTS `sum_channelInfo`;

/*!50001 DROP VIEW IF EXISTS `sum_channelInfo` */;
/*!50001 DROP TABLE IF EXISTS `sum_channelInfo` */;

/*!50001 CREATE TABLE  `sum_channelInfo`(
 `id` bigint(20) ,
 `channelId` bigint(20) ,
 `members` bigint(20) ,
 `time` date ,
 `view24` bigint(20) 
)*/;

/*Table structure for table `sum_rep` */

DROP TABLE IF EXISTS `sum_rep`;

/*!50001 DROP VIEW IF EXISTS `sum_rep` */;
/*!50001 DROP TABLE IF EXISTS `sum_rep` */;

/*!50001 CREATE TABLE  `sum_rep`(
 `messageId` bigint(20) ,
 `adId` bigint(20) ,
 `repChannelId` varchar(16) ,
 `channelId` bigint(20) ,
 `repId` bigint(20) 
)*/;

/*Table structure for table `tmp_forJalil` */

DROP TABLE IF EXISTS `tmp_forJalil`;

/*!50001 DROP VIEW IF EXISTS `tmp_forJalil` */;
/*!50001 DROP TABLE IF EXISTS `tmp_forJalil` */;

/*!50001 CREATE TABLE  `tmp_forJalil`(
 `channelUsername` varchar(200) ,
 `channelName` varchar(200) ,
 `views` bigint(20) ,
 `adId` bigint(20) ,
 `repId` bigint(20) ,
 `repMessageId` bigint(20) ,
 `repChannelId` bigint(20) 
)*/;

/*Table structure for table `tmp_messageText` */

DROP TABLE IF EXISTS `tmp_messageText`;

/*!50001 DROP VIEW IF EXISTS `tmp_messageText` */;
/*!50001 DROP TABLE IF EXISTS `tmp_messageText` */;

/*!50001 CREATE TABLE  `tmp_messageText`(
 `username` varchar(255) ,
 `date` timestamp ,
 `fwdUsername` varchar(255) ,
 `messageText` varchar(6000) 
)*/;

/*Table structure for table `tmp_publisher` */

DROP TABLE IF EXISTS `tmp_publisher`;

/*!50001 DROP VIEW IF EXISTS `tmp_publisher` */;
/*!50001 DROP TABLE IF EXISTS `tmp_publisher` */;

/*!50001 CREATE TABLE  `tmp_publisher`(
 `chatId` bigint(20) ,
 `name` varchar(50) ,
 `phone` varchar(20) ,
 `username` varchar(100) ,
 `firstname` varbinary(1000) ,
 `lastname` varbinary(300) ,
 `type` varchar(20) ,
 `panNumber` varchar(16) ,
 `ibanNumber` varchar(24) ,
 `stickerFileId` varchar(200) ,
 `id` bigint(20) ,
 `channelUsername` varchar(200) ,
 `publisherChatId` bigint(20) ,
 `categoryId` bigint(20) ,
 `channelName` varchar(200) 
)*/;

/*Table structure for table `tmp_vipChannelView` */

DROP TABLE IF EXISTS `tmp_vipChannelView`;

/*!50001 DROP VIEW IF EXISTS `tmp_vipChannelView` */;
/*!50001 DROP TABLE IF EXISTS `tmp_vipChannelView` */;

/*!50001 CREATE TABLE  `tmp_vipChannelView`(
 `id` bigint(20) ,
 `channelName` varchar(200) ,
 `channelUsername` varchar(200) ,
 `viewPerDay` bigint(20) ,
 `price` decimal(21,1) 
)*/;

/*Table structure for table `tpanel_campaign` */

DROP TABLE IF EXISTS `tpanel_campaign`;

/*!50001 DROP VIEW IF EXISTS `tpanel_campaign` */;
/*!50001 DROP TABLE IF EXISTS `tpanel_campaign` */;

/*!50001 CREATE TABLE  `tpanel_campaign`(
 `id` bigint(20) ,
 `name` varchar(256) ,
 `status` varchar(20) ,
 `views` decimal(41,0) ,
 `clicks` decimal(65,0) ,
 `totalClicks` decimal(65,0) 
)*/;

/*View structure for view bot_organizationFinancial */

/*!50001 DROP TABLE IF EXISTS `bot_organizationFinancial` */;
/*!50001 DROP VIEW IF EXISTS `bot_organizationFinancial` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `bot_organizationFinancial` AS (select `a`.`id` AS `id`,`o`.`name` AS `name`,`a`.`name` AS `campaignName`,`a`.`paymentType` AS `bannerInfo`,replace(`a`.`screenshotText`,'\n','') AS `description`,' ' AS `orderDate`,`a`.`createDate` AS `createDate`,`f`.`finalizeDate` AS `finalizeDate`,'TELEGRAM' AS `TYPE`,`f`.`total` AS `cost`,`f`.`viewCount` AS `count` from ((`panel_financial` `f` left join `bot_ad` `a` on((`f`.`adId` = `a`.`id`))) left join `panel_organization` `o` on((`o`.`id` = `a`.`panelOrganizationId`))) order by `f`.`finalizeDate`) */;

/*View structure for view ibot_organizationFinancial */

/*!50001 DROP TABLE IF EXISTS `ibot_organizationFinancial` */;
/*!50001 DROP VIEW IF EXISTS `ibot_organizationFinancial` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `ibot_organizationFinancial` AS (select `a`.`id` AS `id`,`o`.`name` AS `name`,`a`.`name` AS `campaignName`,concat(`a`.`adType`,'-',`a`.`bannerType`) AS `bannerInfo`,replace(`a`.`description`,'\n','') AS `description`,' ' AS `orderDate`,`a`.`createDate` AS `createDate`,`f`.`finalizeDate` AS `finalizeDate`,'INSTAGRAM' AS `TYPE`,`f`.`total` AS `cost`,`f`.`viewCount` AS `count` from ((`ipanel_financial` `f` left join `ibot_ad` `a` on((`f`.`adId` = `a`.`id`))) left join `panel_organization` `o` on((`o`.`id` = `a`.`panelOrganizationId`))) order by `f`.`finalizeDate`) */;

/*View structure for view ibot_publishers */

/*!50001 DROP TABLE IF EXISTS `ibot_publishers` */;
/*!50001 DROP VIEW IF EXISTS `ibot_publishers` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `ibot_publishers` AS (select `p`.`id` AS `id`,`p`.`username` AS `username`,`p`.`name` AS `name`,`c`.`name` AS `category`,`p`.`following` AS `following`,`p`.`followers` AS `followers`,`p`.`posts` AS `posts`,`p`.`engagement` AS `engagement`,`p`.`viewEngagement` AS `viewEngagement`,`p`.`meanViewCount` AS `meanView`,`p`.`meanLikeCount` AS `meanLike`,`p`.`meanCommentCount` AS `meanComment`,`p`.`realFollowerCount` AS `realFollowerCount`,`u`.`username` AS `userAddress`,`u`.`name` AS `adminName`,concat('\'',`u`.`panNumber`) AS `pan`,concat('\'',`u`.`ibanNumber`) AS `iban`,`u`.`chatId` AS `chatId`,`rb`.`pushCount` AS `pushCount`,`rb`.`doneCount` AS `doneCount`,`rb`.`storyDoneAveragePrice` AS `storyDoneAveragePrice`,`rb`.`videoDoneAveragePrice` AS `videoDoneAveragePrice`,`rb`.`photoDoneAveragePrice` AS `photoDoneAveragePrice`,`rb`.`conversion` AS `conversion` from (((`iobs_page` `p` join `ibot_user` `u` on((`p`.`userChatId` = `u`.`chatId`))) join `ibot_category` `c` on((`p`.`categoryId` = `c`.`id`))) left join `ipanel_repBrief` `rb` on((`p`.`id` = `rb`.`pageId`))) where ((`u`.`type` in ('PUBLISHER','TOP_PUBLISHER')) and (`p`.`status` = 'ACCEPTED'))) */;

/*View structure for view ipanel_adStats */

/*!50001 DROP TABLE IF EXISTS `ipanel_adStats` */;
/*!50001 DROP VIEW IF EXISTS `ipanel_adStats` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `ipanel_adStats` AS (select uuid() AS `id`,`r`.`adId` AS `adId`,`v`.`postId` AS `postId`,ceiling(avg(`v`.`views`)) AS `views`,ceiling(avg(`v`.`likes`)) AS `likes`,ceiling(avg(`v`.`comments`)) AS `comments`,concat(substr(`v`.`time`,1,13),':00:00') AS `time` from ((`iobs_postView` `v` join `iobs_post` `p` on((`p`.`postId` = `v`.`postId`))) join `ibot_rep` `r` on((`r`.`postId` = `p`.`id`))) group by `v`.`postId`,`r`.`adId`,substr(`v`.`time`,1,13)) */;

/*View structure for view ipanel_adView */

/*!50001 DROP TABLE IF EXISTS `ipanel_adView` */;
/*!50001 DROP VIEW IF EXISTS `ipanel_adView` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `ipanel_adView` AS (select `a`.`id` AS `adId`,`a`.`name` AS `adName`,`a`.`adType` AS `adType`,`a`.`caption` AS `caption`,`a`.`status` AS `adStatus`,`a`.`campaignId` AS `campaignId`,sum(`d`.`totalClicks`) AS `totalClicks`,sum(`d`.`uniqueClicks`) AS `uniqueClicks`,sum(`r`.`viewCount`) AS `viewCount`,sum(`p`.`videoViews`) AS `views`,sum(`p`.`likes`) AS `likes`,sum(`p`.`comments`) AS `comments` from (((`ibot_ad` `a` left join `ibot_rep` `r` on((`a`.`id` = `r`.`adId`))) left join `shorten_datapoint` `d` on((`d`.`id` = `r`.`datapointId`))) left join `iobs_post` `p` on((`r`.`postId` = `p`.`id`))) group by `a`.`id`) */;

/*View structure for view ipanel_campaign */

/*!50001 DROP TABLE IF EXISTS `ipanel_campaign` */;
/*!50001 DROP VIEW IF EXISTS `ipanel_campaign` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `ipanel_campaign` AS (select `c`.`id` AS `id`,`c`.`name` AS `name`,sum(`v`.`totalClicks`) AS `totalClicks`,sum(`v`.`uniqueClicks`) AS `uniqueClicks`,sum(`v`.`viewCount`) AS `viewCount`,sum(`v`.`views`) AS `views`,sum(`v`.`likes`) AS `likes`,sum(`v`.`comments`) AS `comments` from (`ibot_campaign` `c` left join `ipanel_adView` `v` on((`v`.`campaignId` = `c`.`id`))) group by `c`.`id`) */;

/*View structure for view ipanel_financial */

/*!50001 DROP TABLE IF EXISTS `ipanel_financial` */;
/*!50001 DROP VIEW IF EXISTS `ipanel_financial` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `ipanel_financial` AS (select `r`.`adId` AS `adId`,`ad`.`name` AS `name`,`ad`.`finalizeDate` AS `finalizeDate`,sum(if((`r`.`status` = 'PAID'),`r`.`price`,0)) AS `paid`,sum(if((`r`.`status` = 'SENT'),((`r`.`basePrice` * if(isnull(`r`.`viewLimit`),`r`.`finalViewCount`,least(`r`.`finalViewCount`,`r`.`viewLimit`))) / 1000),0)) AS `left`,(sum(if((`r`.`status` = 'PAID'),`r`.`price`,0)) + sum(if((`r`.`status` = 'SENT'),((`r`.`basePrice` * if(isnull(`r`.`viewLimit`),`r`.`finalViewCount`,least(`r`.`finalViewCount`,`r`.`viewLimit`))) / 1000),0))) AS `total`,sum(if((`r`.`status` = 'NOT_PAY'),((`r`.`basePrice` * `r`.`finalViewCount`) / 1000),0)) AS `notPay`,sum(`r`.`finalViewCount`) AS `viewCount` from (`ibot_rep` `r` join `ibot_ad` `ad`) where ((`r`.`basePrice` is not null) and (`ad`.`id` = `r`.`adId`) and (`ad`.`status` = 'FINALIZED')) group by `r`.`adId`) */;

/*View structure for view ipanel_hashtag */

/*!50001 DROP TABLE IF EXISTS `ipanel_hashtag` */;
/*!50001 DROP VIEW IF EXISTS `ipanel_hashtag` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `ipanel_hashtag` AS (select `h`.`id` AS `id`,`h`.`name` AS `name`,`h`.`status` AS `status`,`h`.`created` AS `created`,`h`.`updated` AS `updated`,`h`.`profilePic` AS `profilePicSrc`,`h`.`postNo` AS `postNo`,sum(`p`.`likes`) AS `likes`,sum(`p`.`comments`) AS `comments`,sum(`p`.`videoViews`) AS `views`,count(0) AS `posts` from ((`iobs_hashtag` `h` join `iobs_hashtagpost` `hp`) join `iobs_post` `p`) where ((`h`.`id` = `hp`.`hashtagId`) and (`hp`.`postId` = `p`.`id`)) group by `h`.`id`) */;

/*View structure for view ipanel_hashtagcomment */

/*!50001 DROP TABLE IF EXISTS `ipanel_hashtagcomment` */;
/*!50001 DROP VIEW IF EXISTS `ipanel_hashtagcomment` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `ipanel_hashtagcomment` AS (select `hp`.`hashtagId` AS `hashtagId`,`c`.`pk` AS `pk`,`c`.`postId` AS `postId`,`c`.`username` AS `username`,`c`.`text` AS `text`,`c`.`comments` AS `comments`,`c`.`likes` AS `likes`,`c`.`created` AS `created` from ((`iobs_hashtagpost` `hp` join `iobs_post` `p`) join `iobs_comment` `c`) where ((`hp`.`postId` = `p`.`id`) and (`p`.`postId` = convert(`c`.`postId` using utf8mb4)))) */;

/*View structure for view ipanel_hashtagpost */

/*!50001 DROP TABLE IF EXISTS `ipanel_hashtagpost` */;
/*!50001 DROP VIEW IF EXISTS `ipanel_hashtagpost` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `ipanel_hashtagpost` AS (select `hp`.`hashtagId` AS `hashtagId`,`p`.`postId` AS `postId`,`p`.`shortcode` AS `shortcode`,`p`.`video` AS `video`,`p`.`videoViews` AS `views`,`p`.`likes` AS `likes`,`p`.`comments` AS `comments`,`p`.`pageId` AS `pageId`,`p`.`createDate` AS `createDate`,`p`.`caption` AS `caption`,`p`.`deleteDate` AS `deleteDate`,`p`.`status` AS `status`,`p`.`id` AS `id`,`p`.`displayUrl` AS `displayUrl`,`p`.`videoUrl` AS `videoUrl` from (`iobs_hashtagpost` `hp` join `iobs_post` `p`) where (`hp`.`postId` = `p`.`id`)) */;

/*View structure for view ipanel_hashtagpoststat */

/*!50001 DROP TABLE IF EXISTS `ipanel_hashtagpoststat` */;
/*!50001 DROP VIEW IF EXISTS `ipanel_hashtagpoststat` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `ipanel_hashtagpoststat` AS (select uuid() AS `id`,`hp`.`hashtagId` AS `hashtagId`,substr(`hp`.`createDate`,1,10) AS `time`,count(0) AS `postNo`,sum(`hp`.`likes`) AS `likes`,sum(`hp`.`comments`) AS `comments`,sum(`hp`.`views`) AS `views` from `ipanel_hashtagpost` `hp` group by `hp`.`hashtagId`,substr(`hp`.`createDate`,1,10)) */;

/*View structure for view ipanel_postView */

/*!50001 DROP TABLE IF EXISTS `ipanel_postView` */;
/*!50001 DROP VIEW IF EXISTS `ipanel_postView` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `ipanel_postView` AS (select uuid() AS `id`,`iobs_postView`.`postId` AS `postId`,ceiling(max(`iobs_postView`.`views`)) AS `views`,ceiling(max(`iobs_postView`.`likes`)) AS `likes`,ceiling(max(`iobs_postView`.`comments`)) AS `comments`,concat(substr(`iobs_postView`.`time`,1,13),':00:00') AS `TIME` from `iobs_postView` group by `iobs_postView`.`postId`,substr(`iobs_postView`.`time`,1,13)) */;

/*View structure for view ipanel_repBrief */

/*!50001 DROP TABLE IF EXISTS `ipanel_repBrief` */;
/*!50001 DROP VIEW IF EXISTS `ipanel_repBrief` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `ipanel_repBrief` AS (select `r`.`pageId` AS `pageId`,count(0) AS `pushCount`,sum(if((`r`.`finalViewCount` > 0),1,0)) AS `doneCount`,ceiling(avg(if(((`a`.`adType` = 'STORY') and (`r`.`finalViewCount` > 0)),`r`.`basePrice`,NULL))) AS `storyDoneAveragePrice`,ceiling(avg(if(((`a`.`adType` = 'POST') and (`a`.`bannerType` = 'VIDEO') and (`r`.`finalViewCount` > 0)),`r`.`basePrice`,NULL))) AS `videoDoneAveragePrice`,ceiling(avg(if(((`a`.`adType` = 'POST') and (`a`.`bannerType` = 'PHOTO') and (`r`.`finalViewCount` > 0)),`r`.`basePrice`,NULL))) AS `photoDoneAveragePrice`,((100 * sum(if(((`r`.`finalViewCount` > 0) and (`d`.`uniqueClicks` is not null)),`d`.`uniqueClicks`,0))) / sum(if(((`r`.`finalViewCount` > 0) and (`d`.`uniqueClicks` is not null)),`r`.`finalViewCount`,0))) AS `conversion` from ((`ibot_rep` `r` left join `ipanel_repView` `d` on((`r`.`id` = `d`.`id`))) left join `ibot_ad` `a` on((`r`.`adId` = `a`.`id`))) group by `r`.`pageId`) */;

/*View structure for view ipanel_repDetail */

/*!50001 DROP TABLE IF EXISTS `ipanel_repDetail` */;
/*!50001 DROP VIEW IF EXISTS `ipanel_repDetail` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `ipanel_repDetail` AS (select `r`.`id` AS `id`,`r`.`publisherChatId` AS `userChatId`,concat('http://advn.ir/',`d`.`name`) AS `link`,`u`.`username` AS `username` from ((`ibot_rep` `r` left join `shorten_datapoint` `d` on((`r`.`datapointId` = `d`.`id`))) left join `ibot_user` `u` on((`r`.`publisherChatId` = `u`.`chatId`)))) */;

/*View structure for view ipanel_repView */

/*!50001 DROP TABLE IF EXISTS `ipanel_repView` */;
/*!50001 DROP VIEW IF EXISTS `ipanel_repView` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `ipanel_repView` AS (select `r`.`id` AS `id`,`r`.`adId` AS `adId`,`ad`.`campaignId` AS `campaignId`,`r`.`pageId` AS `pageId`,`r`.`postId` AS `postId`,`r`.`storyId` AS `storyId`,`r`.`postLink` AS `postLink`,`r`.`status` AS `repStatus`,`r`.`screenshotFileId` AS `screenshotFileId`,`r`.`screenShotType` AS `screenshotType`,`r`.`status` AS `status`,`r`.`viewCount` AS `viewCount`,`d`.`totalClicks` AS `totalClicks`,`d`.`uniqueClicks` AS `uniqueClicks`,`p`.`name` AS `pageName`,`p`.`categoryId` AS `pageCategoryId`,`p`.`profilePic` AS `pageProfilePic`,`p`.`following` AS `pageFollowing`,`p`.`followers` AS `pageFollowers`,`p`.`engagement` AS `pageEngagement`,`p`.`realFollowerCount` AS `pageRealFollowers`,`p`.`posts` AS `pagePosts`,`p`.`username` AS `pageUsername`,`po`.`shortcode` AS `shortcode`,`po`.`video` AS `video`,`po`.`postId` AS `iPostId`,`po`.`videoViews` AS `views`,`po`.`likes` AS `likes`,`po`.`comments` AS `comments`,`po`.`createDate` AS `postCreateDate`,`po`.`deleteDate` AS `postDeleteDate` from ((((`ibot_rep` `r` left join `iobs_page` `p` on((`p`.`id` = `r`.`pageId`))) left join `ibot_ad` `ad` on((`ad`.`id` = `r`.`adId`))) left join `shorten_datapoint` `d` on((`d`.`id` = `r`.`datapointId`))) left join `iobs_post` `po` on((`po`.`id` = `r`.`postId`)))) */;

/*View structure for view obs_adMessage */

/*!50001 DROP TABLE IF EXISTS `obs_adMessage` */;
/*!50001 DROP VIEW IF EXISTS `obs_adMessage` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `obs_adMessage` AS (select `bot_rep`.`observerMessageId` AS `messageId`,substr(`bot_rep`.`observerChatId`,5) AS `channelId`,`bot_rep`.`adId` AS `adId` from `bot_rep` where (`bot_rep`.`observerMessageId` is not null)) union (select `bot_ad`.`observerMessageId` AS `messageId`,substr(`bot_ad`.`observerChatId`,5) AS `channelId`,`bot_ad`.`id` AS `adId` from `bot_ad` where (`bot_ad`.`observerMessageId` is not null)) */;

/*View structure for view obs_banner */

/*!50001 DROP TABLE IF EXISTS `obs_banner` */;
/*!50001 DROP VIEW IF EXISTS `obs_banner` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `obs_banner` AS (select `bot_ad`.`observerMessageId` AS `messageId`,substr(`bot_ad`.`observerChatId`,5) AS `channelId`,`bot_ad`.`id` AS `adId` from `bot_ad`) */;

/*View structure for view obs_rep */

/*!50001 DROP TABLE IF EXISTS `obs_rep` */;
/*!50001 DROP VIEW IF EXISTS `obs_rep` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `obs_rep` AS (select `bot_rep`.`observerMessageId` AS `messageId`,substr(`bot_rep`.`observerChatId`,5) AS `channelId`,`bot_rep`.`adId` AS `adId`,`bot_rep`.`publisherChatId` AS `publisherChatId` from `bot_rep`) */;

/*View structure for view panel_adClickStat */

/*!50001 DROP TABLE IF EXISTS `panel_adClickStat` */;
/*!50001 DROP VIEW IF EXISTS `panel_adClickStat` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `panel_adClickStat` AS select uuid() AS `id`,`a`.`adId` AS `adId`,`a`.`datapointId` AS `datapointId`,ceiling(avg(`o`.`uniqueClicks`)) AS `uniqueClicks`,ceiling(avg(`o`.`totalClicks`)) AS `totalClicks`,concat(substr(`o`.`time`,1,13),':00:00') AS `time` from (`bot_rep` `a` join `shorten_datapointStat` `o`) where (`a`.`datapointId` = `o`.`datapointId`) group by `o`.`datapointId`,substr(`o`.`time`,1,13) */;

/*View structure for view panel_adClicks */

/*!50001 DROP TABLE IF EXISTS `panel_adClicks` */;
/*!50001 DROP VIEW IF EXISTS `panel_adClicks` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `panel_adClicks` AS (select `r`.`adId` AS `adId`,sum(`d`.`uniqueClicks`) AS `clicks`,sum(`d`.`totalClicks`) AS `totalClicks` from (`bot_rep` `r` join `shorten_datapoint` `d`) where (`r`.`datapointId` = `d`.`id`) group by `r`.`adId`) */;

/*View structure for view panel_adStats */

/*!50001 DROP TABLE IF EXISTS `panel_adStats` */;
/*!50001 DROP VIEW IF EXISTS `panel_adStats` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `panel_adStats` AS select uuid() AS `id`,`a`.`id` AS `adId`,`a`.`campaignId` AS `campaignId`,`a`.`originalMessageId` AS `messageId`,0 AS `clicks`,0 AS `totalClicks`,ceiling(avg(`o`.`views`)) AS `views`,concat(substr(`o`.`time`,1,13),':00:00') AS `time` from (`bot_ad` `a` join `obs_messageView` `o`) where ((substr(`a`.`observerChatId`,5) = `o`.`toId`) and (`a`.`observerMessageId` = `o`.`messageId`)) group by `o`.`messageId`,`o`.`toId`,substr(`o`.`time`,1,13) order by `o`.`time` */;

/*View structure for view panel_adStats1 */

/*!50001 DROP TABLE IF EXISTS `panel_adStats1` */;
/*!50001 DROP VIEW IF EXISTS `panel_adStats1` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `panel_adStats1` AS select uuid() AS `id`,`a`.`id` AS `adId`,ceiling(avg(`o`.`views`)) AS `views`,ceiling(avg(`c`.`uniqueClicks`)) AS `clicks`,ceiling(avg(`c`.`totalClicks`)) AS `totalClicks`,concat(substr(`o`.`time`,1,13),':00:00') AS `time` from ((`bot_ad` `a` join `obs_messageView` `o`) left join `panel_adClickStat` `c` on(((`a`.`id` = `c`.`adId`) and (`c`.`time` = concat(substr(`o`.`time`,1,13),':00:00'))))) where ((substr(`a`.`observerChatId`,5) = `o`.`toId`) and (`a`.`observerMessageId` = `o`.`messageId`)) group by `o`.`messageId`,`o`.`toId`,substr(`o`.`time`,1,13) order by `o`.`time` */;

/*View structure for view panel_adView */

/*!50001 DROP TABLE IF EXISTS `panel_adView` */;
/*!50001 DROP VIEW IF EXISTS `panel_adView` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `panel_adView` AS select `a`.`status` AS `adStatus`,`a`.`name` AS `adName`,`a`.`id` AS `adId`,`a`.`campaignId` AS `campaignId`,`a`.`originalMessageId` AS `messageId`,`o`.`views` AS `views`,`panel_adClicks`.`clicks` AS `clicks`,`panel_adClicks`.`totalClicks` AS `totalClicks` from ((`bot_ad` `a` left join `obs_message` `o` on(((substr(`a`.`observerChatId`,5) = `o`.`toId`) and (`a`.`observerMessageId` = `o`.`messageId`)))) left join `panel_adClicks` on((`panel_adClicks`.`adId` = `a`.`id`))) */;

/*View structure for view panel_aggregateAdView */

/*!50001 DROP TABLE IF EXISTS `panel_aggregateAdView` */;
/*!50001 DROP VIEW IF EXISTS `panel_aggregateAdView` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `panel_aggregateAdView` AS (select `panel_adView`.`campaignId` AS `campaignId`,`panel_adView`.`messageId` AS `messageId`,sum(`panel_adView`.`clicks`) AS `clicks`,sum(`panel_adView`.`totalClicks`) AS `totalClicks`,max(`panel_adView`.`views`) AS `views` from `panel_adView` group by `panel_adView`.`campaignId`,`panel_adView`.`messageId`) */;

/*View structure for view panel_channelDetail */

/*!50001 DROP TABLE IF EXISTS `panel_channelDetail` */;
/*!50001 DROP VIEW IF EXISTS `panel_channelDetail` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `panel_channelDetail` AS (select `pc`.`id` AS `id`,`pc`.`categoryId` AS `categoryId`,`bc`.`name` AS `categoryName`,`pc`.`channelName` AS `channelName`,`pc`.`channelUsername` AS `channelUsername`,`oc`.`members` AS `members`,`oc`.`view24` AS `view24`,`rb`.`pushCount` AS `pushCount`,`rb`.`doneCount` AS `doneCount`,`rb`.`cpmDoneAveragePrice` AS `cpmDoneAveragePrice`,`rb`.`cpcDoneAveragePrice` AS `cpcDoneAveragePrice`,`rb`.`clickAvg` AS `clickAvg`,`rb`.`conversion` AS `conversion` from (((`bot_publisherChannel` `pc` left join `bot_category` `bc` on((`bc`.`id` = `pc`.`categoryId`))) left join `obs_chat` `oc` on((`oc`.`id` = `pc`.`chatId`))) left join `panel_repBrief` `rb` on((`pc`.`id` = `rb`.`publisherChannelId`))) where (`pc`.`status` = 'ACCEPTED') group by `pc`.`id`) */;

/*View structure for view panel_cpaStat */

/*!50001 DROP TABLE IF EXISTS `panel_cpaStat` */;
/*!50001 DROP VIEW IF EXISTS `panel_cpaStat` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `panel_cpaStat` AS (select `c`.`channelUsername` AS `channelUsername`,sum(`r`.`finalViewCount`) AS `views`,sum(((`r`.`basePrice` * `r`.`finalViewCount`) / 1000)) AS `costs`,sum(`r`.`installCount`) AS `installs`,sum((`a`.`installBasePrice` * `r`.`installCount`)) AS `incomes`,count(distinct `r`.`adId`) AS `campaings`,round(max(((1000 * `r`.`installCount`) / `r`.`finalViewCount`)),2) AS `maxConversion`,round(min(((1000 * `r`.`installCount`) / `r`.`finalViewCount`)),2) AS `minConversion` from ((`bot_rep` `r` join `bot_publisherChannel` `c`) join `bot_ad` `a`) where ((`r`.`adId` = `a`.`id`) and (`c`.`id` = `r`.`publisherChannelId`) and (`r`.`installCount` is not null) and (`r`.`finalViewCount` > 0)) group by `c`.`id`) */;

/*View structure for view panel_financial */

/*!50001 DROP TABLE IF EXISTS `panel_financial` */;
/*!50001 DROP VIEW IF EXISTS `panel_financial` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `panel_financial` AS (select `r`.`adId` AS `adId`,`ad`.`name` AS `name`,`ad`.`finalizeDate` AS `finalizeDate`,sum(if((`r`.`status` = 'PAID'),`r`.`price`,0)) AS `paid`,sum(if((`r`.`status` = 'SENT'),((`r`.`basePrice` * `r`.`finalViewCount`) / if((`ad`.`paymentType` = 'CLICK'),1,1000)),0)) AS `left`,(sum(if((`r`.`status` = 'PAID'),`r`.`price`,0)) + sum(if((`r`.`status` = 'SENT'),((`r`.`basePrice` * `r`.`finalViewCount`) / if((`ad`.`paymentType` = 'CLICK'),1,1000)),0))) AS `total`,sum(if((`r`.`status` = 'NOT_PAY'),((`r`.`basePrice` * `r`.`finalViewCount`) / if((`ad`.`paymentType` = 'CLICK'),1,1000)),0)) AS `notPay`,sum(`r`.`finalViewCount`) AS `viewCount` from ((`bot_rep` `r` join `bot_ad` `ad`) join `bot_publisherChannel` `c`) where ((`r`.`basePrice` is not null) and (`ad`.`id` = `r`.`adId`) and (`r`.`publisherChannelId` = `c`.`id`) and (`r`.`finalViewCount` > 0) and (`ad`.`status` = 'FINALIZED') and (`c`.`type` = 'NORMAL')) group by `r`.`adId`) */;

/*View structure for view panel_message */

/*!50001 DROP TABLE IF EXISTS `panel_message` */;
/*!50001 DROP VIEW IF EXISTS `panel_message` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `panel_message` AS (select concat(`obs_message`.`toId`,'-',`obs_message`.`messageId`,'-',`obs_message`.`observerId`) AS `id`,`obs_message`.`toId` AS `toId`,`obs_message`.`messageId` AS `messageId`,`obs_message`.`fromId` AS `fromId`,`obs_message`.`fwdFromId` AS `fwdFromId`,`obs_message`.`fwdDate` AS `fwdDate`,`obs_message`.`fwdChannelId` AS `fwdChannelId`,`obs_message`.`fwdChannelPost` AS `fwdChannelPost`,`obs_message`.`message` AS `message`,`obs_message`.`views` AS `views`,`obs_message`.`date` AS `createDate`,`obs_message`.`observerId` AS `observerId`,`obs_message`.`deleted` AS `deleteDate`,`obs_chat`.`username` AS `username`,`obs_chat`.`id` AS `channelId` from (`obs_chat` join `obs_message`) where ((`obs_message`.`observerId` = `obs_chat`.`observerId`) and (`obs_message`.`toId` = `obs_chat`.`id`))) */;

/*View structure for view panel_repBrief */

/*!50001 DROP TABLE IF EXISTS `panel_repBrief` */;
/*!50001 DROP VIEW IF EXISTS `panel_repBrief` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `panel_repBrief` AS (select `r`.`publisherChannelId` AS `publisherChannelId`,count(0) AS `pushCount`,sum(if((`r`.`finalViewCount` > 0),1,0)) AS `doneCount`,ceiling(avg(if(((`a`.`paymentType` = 'VIEW') and (`r`.`finalViewCount` > 0)),`r`.`basePrice`,NULL))) AS `cpmDoneAveragePrice`,ceiling(avg(if(((`a`.`paymentType` = 'CLICK') and (`r`.`finalViewCount` > 0)),`r`.`basePrice`,NULL))) AS `cpcDoneAveragePrice`,ceiling(avg(if(((`a`.`paymentType` = 'CLICK') and (`r`.`finalViewCount` > 0)),`r`.`finalViewCount`,NULL))) AS `clickAvg`,((100 * sum(if(((`r`.`finalViewCount` > 0) and (`d`.`clicks` is not null)),`d`.`clicks`,0))) / sum(if(((`r`.`finalViewCount` > 0) and (`d`.`clicks` is not null)),`d`.`views`,0))) AS `conversion` from ((`bot_rep` `r` left join `panel_repView` `d` on((`r`.`id` = `d`.`id`))) left join `bot_ad` `a` on((`r`.`adId` = `a`.`id`))) group by `r`.`publisherChannelId`) */;

/*View structure for view panel_repView */

/*!50001 DROP TABLE IF EXISTS `panel_repView` */;
/*!50001 DROP VIEW IF EXISTS `panel_repView` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `panel_repView` AS (select `br`.`id` AS `id`,`bc`.`channelUsername` AS `channelUsername`,`bc`.`channelName` AS `channelName`,`bc`.`id` AS `channelId`,`om`.`views` AS `views`,`br`.`adId` AS `adId`,`br`.`viewCount` AS `viewCount`,`br`.`postLink` AS `postLink`,`br`.`status` AS `repStatus`,`br`.`messageId` AS `messageId`,`br`.`basePrice` AS `basePrice`,`br`.`conversionValue` AS `conversionValue`,`br`.`installCount` AS `installCount`,`br`.`subCount` AS `subCount`,`br`.`finalViewCount` AS `finalViewCount`,`br`.`fromChatId` AS `channelChatId`,`br`.`fileName` AS `fileName`,`bc`.`chatId` AS `realChannelChatId`,`dp`.`uniqueClicks` AS `clicks`,`dp`.`totalClicks` AS `totalClicks` from (((`bot_rep` `br` left join `bot_publisherChannel` `bc` on((`br`.`publisherChannelId` = `bc`.`id`))) left join `obs_message` `om` on(((substr(`br`.`observerChatId`,5) = `om`.`toId`) and (`br`.`observerMessageId` = `om`.`messageId`)))) left join `shorten_datapoint` `dp` on((`dp`.`id` = `br`.`datapointId`)))) */;

/*View structure for view shorten_aggregatedatapointstat */

/*!50001 DROP TABLE IF EXISTS `shorten_aggregatedatapointstat` */;
/*!50001 DROP VIEW IF EXISTS `shorten_aggregatedatapointstat` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `shorten_aggregatedatapointstat` AS (select `shorten_click`.`datapointId` AS `datapointId`,sum(if((`shorten_click`.`processed` > 0),1,0)) AS `totalClicks`,sum(if((`shorten_click`.`processed` = 1),1,0)) AS `uniqueClicks`,sum(if((`shorten_click`.`processed` = 9),1,0)) AS `botClicks` from `shorten_click` group by `shorten_click`.`datapointId`) */;

/*View structure for view shortner_adjust */

/*!50001 DROP TABLE IF EXISTS `shortner_adjust` */;
/*!50001 DROP VIEW IF EXISTS `shortner_adjust` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `shortner_adjust` AS (select concat(`shorten_event`.`ci`,'-',substr(`shorten_event`.`time`,1,10)) AS `type`,count(0) AS `total`,substr(`shorten_event`.`time`,1,10) AS `date` from `shorten_event` group by `shorten_event`.`ci`,substr(`shorten_event`.`time`,1,10)) */;

/*View structure for view sum_banner */

/*!50001 DROP TABLE IF EXISTS `sum_banner` */;
/*!50001 DROP VIEW IF EXISTS `sum_banner` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `sum_banner` AS (select `bot_ad`.`id` AS `adId`,substr(`bot_ad`.`originalChatId`,5) AS `channelId`,`bot_ad`.`originalMessageId` AS `messageId`,`bot_ad`.`createDate` AS `createDate` from `bot_ad`) */;

/*View structure for view sum_channel */

/*!50001 DROP TABLE IF EXISTS `sum_channel` */;
/*!50001 DROP VIEW IF EXISTS `sum_channel` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `sum_channel` AS (select `c`.`username` AS `username`,`i`.`members` AS `members`,ceiling(avg(`i`.`view24`)) AS `view24` from (`sum_channelInfo` `i` join `obs_channel` `c`) where (`c`.`channelId` = `i`.`channelId`) group by `c`.`username`) */;

/*View structure for view sum_channelInfo */

/*!50001 DROP TABLE IF EXISTS `sum_channelInfo` */;
/*!50001 DROP VIEW IF EXISTS `sum_channelInfo` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `sum_channelInfo` AS (select `obs_channelInfo`.`id` AS `id`,`obs_channelInfo`.`channelId` AS `channelId`,`obs_channelInfo`.`members` AS `members`,`obs_channelInfo`.`time` AS `time`,`obs_channelInfo`.`view24` AS `view24` from `obs_channelInfo` order by `obs_channelInfo`.`time` desc) */;

/*View structure for view sum_rep */

/*!50001 DROP TABLE IF EXISTS `sum_rep` */;
/*!50001 DROP VIEW IF EXISTS `sum_rep` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `sum_rep` AS (select `r`.`messageId` AS `messageId`,`r`.`adId` AS `adId`,substr(`r`.`fromChatId`,5) AS `repChannelId`,`c`.`chatId` AS `channelId`,`r`.`id` AS `repId` from (`bot_rep` `r` join `bot_publisherChannel` `c`) where ((`r`.`publisherChannelId` = `c`.`id`) and (`r`.`fromChatId` is not null) and (`r`.`messageId` is not null) and (`c`.`chatId` is not null))) */;

/*View structure for view tmp_forJalil */

/*!50001 DROP TABLE IF EXISTS `tmp_forJalil` */;
/*!50001 DROP VIEW IF EXISTS `tmp_forJalil` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `tmp_forJalil` AS select `p`.`channelUsername` AS `channelUsername`,`p`.`channelName` AS `channelName`,`m`.`views` AS `views`,`r`.`adId` AS `adId`,`r`.`id` AS `repId`,`r`.`messageId` AS `repMessageId`,`r`.`fromChatId` AS `repChannelId` from ((`bot_rep` `r` join `obs_message` `m`) join `tmp_publisher` `p`) where ((`r`.`publisherChatId` = `p`.`chatId`) and (substr(`r`.`observerChatId`,5) = `m`.`toId`) and (`r`.`observerMessageId` = `m`.`messageId`)) */;

/*View structure for view tmp_messageText */

/*!50001 DROP TABLE IF EXISTS `tmp_messageText` */;
/*!50001 DROP VIEW IF EXISTS `tmp_messageText` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `tmp_messageText` AS (select `c`.`username` AS `username`,`m`.`date` AS `date`,`c1`.`username` AS `fwdUsername`,`mt`.`messageText` AS `messageText` from (((`obs_messageText` `mt` join `obs_chat` `c` on(((`c`.`id` = `mt`.`toId`) and (`c`.`observerId` = `mt`.`observerId`) and (`c`.`username` is not null)))) join `obs_message` `m` on(((`m`.`toId` = `mt`.`toId`) and (`m`.`messageId` = `mt`.`messageId`) and (`mt`.`observerId` = `m`.`observerId`)))) left join `obs_chat` `c1` on(((`c1`.`id` = `m`.`fwdChannelId`) and (`c1`.`observerId` = `m`.`observerId`))))) */;

/*View structure for view tmp_publisher */

/*!50001 DROP TABLE IF EXISTS `tmp_publisher` */;
/*!50001 DROP VIEW IF EXISTS `tmp_publisher` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `tmp_publisher` AS select `u`.`chatId` AS `chatId`,`u`.`name` AS `name`,`u`.`phone` AS `phone`,`u`.`username` AS `username`,`u`.`firstname` AS `firstname`,`u`.`lastname` AS `lastname`,`u`.`type` AS `type`,`u`.`panNumber` AS `panNumber`,`u`.`ibanNumber` AS `ibanNumber`,`u`.`stickerFileId` AS `stickerFileId`,`c`.`id` AS `id`,`c`.`channelUsername` AS `channelUsername`,`c`.`publisherChatId` AS `publisherChatId`,`c`.`categoryId` AS `categoryId`,`c`.`channelName` AS `channelName` from (`bot_user` `u` join `bot_publisherChannel` `c`) where (`u`.`chatId` = `c`.`publisherChatId`) */;

/*View structure for view tmp_vipChannelView */

/*!50001 DROP TABLE IF EXISTS `tmp_vipChannelView` */;
/*!50001 DROP VIEW IF EXISTS `tmp_vipChannelView` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `tmp_vipChannelView` AS (select `tmp_vipChannel`.`id` AS `id`,`tmp_vipChannel`.`channelName` AS `channelName`,`tmp_vipChannel`.`channelUsername` AS `channelUsername`,`tmp_vipChannel`.`viewPerDay` AS `viewPerDay`,(`tmp_vipChannel`.`price` * 1.5) AS `price` from `tmp_vipChannel`) */;

/*View structure for view tpanel_campaign */

/*!50001 DROP TABLE IF EXISTS `tpanel_campaign` */;
/*!50001 DROP VIEW IF EXISTS `tpanel_campaign` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `tpanel_campaign` AS (select `c`.`id` AS `id`,`c`.`name` AS `name`,`c`.`status` AS `status`,sum(`ad`.`views`) AS `views`,sum(`ad`.`clicks`) AS `clicks`,sum(`ad`.`totalClicks`) AS `totalClicks` from (`ibot_campaign` `c` left join `panel_aggregateAdView` `ad` on((`ad`.`campaignId` = `c`.`id`))) group by `c`.`id`) */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
