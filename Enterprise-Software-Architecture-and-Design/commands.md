1. To SSH into Amazon EC2 instance:
$ ssh -i <private_key_file>.pem ec2-user@<public_ip>

2. After typing 1, if you get below error,
It is required that your private key files are NOT accessible by others.
This private key will be ignored.
Load key "ec2-glassfish-keypair.pem": bad permissions
Permission denied (publickey).

$ chmod 400 <private_key_file>.pem
