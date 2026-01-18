-- Fix Email in BOTH Seller and VerificationCode tables
-- Change .con to .com

-- Step 1: Check current state
SELECT 'Seller Table' as table_name, id, email, is_email_verified 
FROM seller 
WHERE email LIKE '%rishicoding9838%'
UNION ALL
SELECT 'VerificationCode Table' as table_name, id, email, NULL as is_email_verified
FROM verification_code 
WHERE email LIKE '%rishicoding9838%' OR otp = '074790';

-- Step 2: Update Seller Table
UPDATE seller 
SET email = 'rishicoding9838@gmail.com' 
WHERE email = 'rishicoding9838@gmail.con';

-- Step 3: Update VerificationCode Table  
UPDATE verification_code 
SET email = 'rishicoding9838@gmail.com' 
WHERE email = 'rishicoding9838@gmail.con';

-- Step 4: Verify both tables are updated
SELECT 'After Update - Seller' as table_name, id, email, is_email_verified 
FROM seller 
WHERE email LIKE '%rishicoding9838%'
UNION ALL
SELECT 'After Update - VerificationCode' as table_name, id, email, NULL
FROM verification_code 
WHERE email LIKE '%rishicoding9838%' OR otp = '074790';
