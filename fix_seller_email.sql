-- Fix the seller email typo in database
-- Change .con to .com

-- First, check what's in VerificationCode table for OTP 074790
SELECT * FROM verification_code WHERE otp = '074790';

-- Check seller table
SELECT id, email, is_email_verified FROM seller WHERE email LIKE '%rishicoding9838%';

-- Option 1: Update VerificationCode email to match Seller table (.con)
-- UPDATE verification_code SET email = 'rishicoding9838@gmail.con' WHERE otp = '074790';

-- Option 2: Update Seller email to .com (RECOMMENDED)
UPDATE seller SET email = 'rishicoding9838@gmail.com' WHERE email = 'rishicoding9838@gmail.con';

-- After updating, verify
SELECT id, email, is_email_verified FROM seller WHERE email LIKE '%rishicoding9838%';
