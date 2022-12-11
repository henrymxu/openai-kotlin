touch android/.env
# see android/.env.template
printf "OPENAI_API_KEY=%s\n" $OPENAI_API_KEY >> android/.env

touch web/.env
# see web/.env.template
printf "REACT_APP_OPENAI_API_KEY=%s\n" $OPENAI_API_KEY >> web/.env

touch ios/Config.xcconfig
# see ios/Config.xcconfig.template
printf "OPENAI_API_KEY=%s\n" $OPENAI_API_KEY >> ios/Config.xcconfig