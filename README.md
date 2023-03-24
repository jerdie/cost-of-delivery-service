# Cost of delivery service
### API:

#### To test using curl:


 ``
 curl --location 'http://ec2-3-27-75-152.ap-southeast-2.compute.amazonaws.com:8080/api/parcel/cost' \
 --header 'Content-Type: application/json' \
 --data '{
 "width":1,
 "height":1,
 "length":1,
 "weight":50,
 "voucher":"MYNT"
 }'
 ``