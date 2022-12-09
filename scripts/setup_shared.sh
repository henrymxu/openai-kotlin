touch android/.env
# see android/.env.template
printf "OPENAI_API_KEY=%s\n" $OPENAI_API_KEY >> android/.env

touch web/.env
# see web/.env.template
printf "REACT_APP_OPENAI_API_KEY=%s\n" $OPENAI_API_KEY >> web/.env
