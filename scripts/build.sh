#!/usr/bin/env bash
set -euo pipefail
cd ..
./gradlew build -Pwhitelotus_storePassword="${WHITELOTUS_STOREPASSWORD}" \
  -Pwhitelotus_keyAlias="${WHITELOTUS_KEYALIAS}" \
  -Pwhitelotus_keyPassword="${WHITELOTUS_KEYPASSWORD}"
