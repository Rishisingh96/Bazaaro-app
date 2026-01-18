-- Fix Product Table - Add AUTO_INCREMENT to id column
-- Run this SQL in DBeaver or MySQL client

-- Step 1: Check current table structure
DESCRIBE product;

-- Step 2: Check if id column has AUTO_INCREMENT
SHOW CREATE TABLE product;

-- Step 3: Fix the id column to have AUTO_INCREMENT
ALTER TABLE product MODIFY COLUMN id BIGINT AUTO_INCREMENT;

-- Step 4: Verify the change
DESCRIBE product;

-- Alternative: If above doesn't work, drop and recreate (WARNING: This will delete all data)
-- DROP TABLE IF EXISTS product;
-- Then let Hibernate recreate it with correct settings
