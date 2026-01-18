-- Complete Fix for Product Table AUTO_INCREMENT Issue
-- Run this in DBeaver SQL Editor

-- Step 1: Check current table structure
SELECT COLUMN_NAME, COLUMN_TYPE, EXTRA 
FROM INFORMATION_SCHEMA.COLUMNS 
WHERE TABLE_SCHEMA = 'BazaaroApp' 
AND TABLE_NAME = 'product' 
AND COLUMN_NAME = 'id';

-- Step 2: If id column doesn't have AUTO_INCREMENT, fix it
ALTER TABLE product MODIFY COLUMN id BIGINT NOT NULL AUTO_INCREMENT;

-- Step 3: Verify the fix
DESCRIBE product;

-- Step 4: Check if AUTO_INCREMENT is properly set
SHOW CREATE TABLE product;

-- If you see "AUTO_INCREMENT" in the output, it's fixed!
