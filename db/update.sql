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


