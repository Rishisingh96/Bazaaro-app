-- Seller Email Update Script
-- Run this in DBeaver SQL Editor

-- Step 1: Check current data
SELECT id, email, is_email_verified FROM seller WHERE email LIKE '%rishicoding9838%';

-- Step 2: Check VerificationCode table
SELECT * FROM verification_code WHERE otp = '074790';

-- Step 3: UPDATE Seller email (Fix .con to .com)
UPDATE seller 
SET email = 'rishicoding9838@gmail.com' 
WHERE email = 'rishicoding9838@gmail.con';

-- Step 4: UPDATE VerificationCode email to match (if needed)
UPDATE verification_code 
SET email = 'rishicoding9838@gmail.com' 
WHERE email = 'rishicoding9838@gmail.con';

-- Step 5: Verify the update
SELECT s.id, s.email as seller_email, s.is_email_verified,
       vc.otp, vc.email as verification_email
FROM seller s
LEFT JOIN verification_code vc ON s.email = vc.email
WHERE s.email LIKE '%rishicoding9838%';
