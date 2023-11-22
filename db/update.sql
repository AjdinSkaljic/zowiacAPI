

#App in englisch

# add colums: name_en und category_en
alter table hide_types
    add name_en VARCHAR(64) null after name;


SET FOREIGN_KEY_CHECKS=0;
delete from animals;

#Import from file
SET FOREIGN_KEY_CHECKS=1;





ALTER TABLE `zowiac_map`.`users`
    CHANGE COLUMN `zowiac` `language` VARCHAR(2) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_bin' NOT  NULL DEFAULT 'DE';


alter table evidence_types
    add name_en varchar(64) null;





ALTER TABLE `zowiac_map`.`animals`
    CHANGE COLUMN `zowiac` `report_type` VARCHAR(1) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_bin' NULL DEFAULT NULL ;

ALTER TABLE `zowiac_map`.`animals`
    ADD COLUMN `first_describer` VARCHAR(128) NULL AFTER `report_type`,
ADD COLUMN `actual_population` VARCHAR(2) NULL AFTER `first_describer`,
ADD COLUMN `longterm_population` VARCHAR(2) NULL AFTER `actual_population`;

ALTER TABLE `zowiac_map`.`shooting_seasons`
    CHANGE COLUMN `state` `state` VARCHAR(2) NULL DEFAULT NULL AFTER `animal_id`;

ALTER TABLE `zowiac_map`.`shooting_seasons`
    ADD COLUMN `date_from1` DATE NULL DEFAULT NULL AFTER `date_to`,
ADD COLUMN `date_to1` DATE NULL DEFAULT NULL AFTER `date_from1`;





ALTER TABLE `zowiac_map`.`reports`
    ADD COLUMN `sub_animal_id` INT NULL DEFAULT NULL AFTER `area_id`;
Update  zowiac_map.shooting_seasons set state = 'BU' where state = ''


ToDO:

CREATE TABLE `orders` (
                          `id` int NOT NULL AUTO_INCREMENT,
                          `name` varchar(256) default NULL,
                          `street` varchar(256) default NULL,
                          `zip` varchar(16) default NULL,
                          `city` varchar(256) default NULL,
                          `count_visitors` int default NULL,
                          `count_posters` int default NULL,
                          `receipt_id` int default NULL,
                          `remark` varchar(512) default NULL,
                          `create_datetime` datetime DEFAULT NULL,
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 ;



CREATE TABLE `order_positions` (
                                   `id` int NOT NULL AUTO_INCREMENT,
                                   `order_id` int NOT NULL,
                                   `type` VARCHAR(1) NOT NULL,
                                   `name` varchar(256) default NULL,
                                   PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 ;



CREATE TABLE `order_logs` (
                                   `id` int NOT NULL AUTO_INCREMENT,
                                   `order_id` int NOT NULL,
                                   `date_time` datetime NOT NULL,
                                   `username` varchar(128) default NULL,
                                   `message` varchar(512) default NULL,
                                   PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 ;