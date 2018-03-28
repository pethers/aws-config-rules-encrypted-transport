# hack23-config-rules-encrypted-transport

Create rules for loadbalancer_https_only rds_force_ssl_enabled


Recommend using below for data at rest
https://docs.aws.amazon.com/config/latest/developerguide/rds-storage-encrypted.html and 
https://docs.aws.amazon.com/config/latest/developerguide/encrypted-volumes.html

and make sure vpc flow logs are enabled
https://github.com/awslabs/aws-config-rules/blob/master/python/vpc_flow_logs_enabled.py
