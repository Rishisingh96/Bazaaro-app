```toml
name = 'Create Seller'
method = 'GET'
url = 'http://localhost:8080/sellers'
sortWeight = 1000000
id = 'b5d1ff5b-46d6-4f23-a4e5-6ad64e17f434'

[body]
type = 'JSON'
raw = '''
{
  "sellerName": "Rishi Singh",
  "mobile": "7800017055",
  "email": "rishijob9838@gmail.com",
  "password": "strongPassword123",
  "businessDetails": {
    "businessName": "RishiTech Pvt Ltd",
    "businessEmail": "contact@rishitech.com",
    "businessMobile": "9876543210",
    "logo": "logo_url_or_base64",
    "banner": "banner_url_or_base64"
  },
  "bankDetails": {
    "accountNumber": "123456789012",
    "accountHolderName": "Rishi Singh",
    "ifscCode": "SBIN0001234"
  },
  "pickupAddress": {
    "name": "Saloni Suryavanshi",
    "locality": "MG Road Area",
    "address": "101 MG Road",
    "city": "Indore",
    "state": "Madhya Pradesh",
    "pinCode": "452001",
    "mobile": "7800017055"
  },
  "GSTIN": "22AAAAA0000A1Z5"
}'''
```
