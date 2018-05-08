CREATE DATABASE IF NOT EXISTS `ec_background_process_feedback`;

use ec_background_process_feedback;

CREATE TABLE IF NOT EXISTS `process` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `client` VARCHAR(10),
  `name` VARCHAR(255) NOT NULL,
  `app` VARCHAR(255) NOT NULL,
  `current_step` int(20) NOT NULL,
  `total_steps` int(20) NOT NULL,
  `status` ENUM('RUNNING', 'ERROR', 'SUCCESS') NOT NULL DEFAULT 'RUNNING',
  `date_created` DATETIME NOT NULL,
  `last_updated` DATETIME NOT NULL,
  `created_by` VARCHAR(255) DEFAULT 'System',
  `updated_by` VARCHAR(255) DEFAULT 'System',
  PRIMARY KEY (`id`),
  INDEX `idx_client` (`client`),
  INDEX `idx_name` (`name`),
  INDEX `idx_status` (`status`),
  INDEX `idx_date_created` (`date_created`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;