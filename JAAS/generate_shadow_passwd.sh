#!/bin/bash

echo "aquest script creara un arxiu de text amb el següent format:"
echo "login:hashedpasswd:salt"


OUTPUT_FILE=$2
rm $OUTPUT_FILE

PEPPER=$(shuf -i 0-1000 -n 1)
SALT=$(od -vAn -N4 -to < /dev/random | tr -d ' ')
LOGIN_PASSWORD_LIST=$(cat $1)

for line in $LOGIN_PASSWORD_LIST;do
  echo "###############################"
  LOGIN=$(echo $line | cut -d':' -f1)
  PASSWORD=$(echo $line | cut -d':' -f2)

  echo "Login: $LOGIN"
  echo "Password: $PASSWORD"
  echo "Pepper: $PEPPER"
  echo "Salt: $SALT"

  PASSWORD_TO_HASH=$PASSWORD$PEPPER$SALT
  echo "contrasenya preparada per fer el hash: $PASSWORD_TO_HASH"
  HASHBYSHASUM=$(echo -n $PASSWORD_TO_HASH | shasum -a 512)
  HASH_READY=$(echo $HASHBYSHASUM | cut -d' ' -f1)
  echo "HASHBYSHASUM: $HASHBYSHASUM"
  echo "HASH_READY: $HASH_READY"

  echo
  echo "Preparat per escriure en OUTPUT_FILE"
  echo "login:hashedpasswd:salt"
  echo
  echo "$LOGIN:$HASH_READY:$SALT"
  echo "$LOGIN:$HASH_READY:$SALT" >> $OUTPUT_FILE
   

done
