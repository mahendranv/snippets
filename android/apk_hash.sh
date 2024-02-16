#!/bin/sh
echo "APK: [$1]"

echo "---------- Computed -----------"
echo "Checksum: "

apksigner verify -print-certs --verbose $1 | grep 'Signer #1 certificate SHA-256 digest:.*' | awk '{print $NF}'| xxd -r -p | openssl base64  | tr -- '+/' '-_' | awk '{print $0"\\n"}'

# apksigner verify -print-certs $1 | perl -nle 'print $& if m{(?<=SHA-256 digest:) .*}' | xxd -r -p | openssl base64 | tr -d '=' | tr -- '+/=' '-_'

echo "\nVersion info: "
aapt dump badging $1 | grep versionCode
echo "--------------------------------"