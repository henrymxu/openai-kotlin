import React, {useState} from 'react';
import './App.css';
import {
    AppBar,
    Box, IconButton, InputAdornment,
    Stack,
    TextField, Toolbar, Typography
} from "@mui/material";
import SendIcon from '@mui/icons-material/Send';
import {Settings} from "@mui/icons-material";
import {ChatMessage, ChatWindow} from "./Chat";
import {SettingsDialog} from "./SettingsDialog";
import { OpenAiClient } from 'openaikotlin';

function scrollChatToBottom() {
    let objDiv = document.getElementById("chat-window");
    if (objDiv !== null) {
        objDiv.scrollTop = objDiv.scrollHeight;
    }
}

function App(props: { apiKey: string }) {
    const client = new OpenAiClient({apiKey: props.apiKey});

    const initialChat = [{sender: 'openai', message: 'Welcome to GPT Chat!'}]
    const [input, setInput] = useState("");
    const [error, setError] = useState("");
    const [chat, setChat] = useState<ChatMessage[]>(initialChat);

    const [settingsOpen, setSettingsOpen] = useState(false);
    const [models, setModels] = useState<string[]>(['text-davinci-003']);
    const [model, setModel] = useState('text-davinci-003');
    const [maxTokens, setMaxTokens] = useState<number | undefined>();

    const onMessageReceived = (message: openaikotlin.Response<openaikotlin.Completion>) => {
        if (message.error) {
            setError(message.error.message)
        } else {
            const chatMessages = message.result!.choices.map(choice => {
                return {sender: 'openai', message: choice.text}
            })
            setChat((prevState) => prevState.concat(chatMessages))
        }
        scrollChatToBottom()
    }

    const sendMessage = (message: string) => {
        setChat((prevState) => prevState.concat({sender: '', message: message}))
        // @ts-ignore
        client.createCompletionStream({
            model: model,
            prompt: message,
            maxTokens: maxTokens
        }, onMessageReceived)
        scrollChatToBottom()
        setError('')
        setInput("")
    }

    const loadModels = () => {
        client.listModels().then((result) => {
            if (result.result) {
                setModels(result!.result.data.map(item => item.id))
            }
        })
    }

    return (
        <div className="App">
            <header className="App-header">
                <Stack width={'100%'} height={'100%'} alignItems={'center'} justifyContent={'center'}
                       sx={{position: 'fixed'}}>
                    <SettingsDialog open={settingsOpen}
                                    models={models}
                                    initialModel={model}
                                    initialMaxTokens={maxTokens}
                                    onSave={(model, maxTokens) => {
                                        setModel(model)
                                        setMaxTokens(maxTokens)
                                        setSettingsOpen(false)
                                    }}
                    />
                    <AppBar position="static">
                        <Toolbar>
                            <Typography variant="h6" component="div" sx={{textAlign: 'start', flexGrow: 1}}>
                                OpenAI Kotlin Sample Web App
                            </Typography>
                            <IconButton size="large" edge="end" color="inherit"
                                        aria-label="menu" sx={{mr: 2}}
                                        onClick={() => {
                                            loadModels()
                                            setSettingsOpen(true)
                                        }}
                            >
                                <Settings/>
                            </IconButton>
                        </Toolbar>
                    </AppBar>
                    <ChatWindow chat={chat}/>
                    <Box sx={{m: 1}}/>
                    <TextField autoComplete="off" sx={{width: '75%'}}
                               value={input}
                               label={'Enter your prompt'}
                               error={error !== ""}
                               helperText={error}
                               onChange={(value) => {
                                   setError('')
                                   setInput(value.target.value)
                               }}
                               onKeyDown={(e) => {
                                   if (e.key === "Enter") {
                                       sendMessage(input)
                                   }
                               }}
                               InputProps={{
                                   endAdornment: (
                                       <InputAdornment position="end">
                                           <IconButton aria-label="send"
                                                       onClick={() => sendMessage(input)}
                                                       edge="end">
                                               <SendIcon/>
                                           </IconButton>
                                       </InputAdornment>
                                   ),
                               }}/>
                    <Box sx={{m: 1}}/>
                </Stack>
            </header>
        </div>
    );
}

export default App;
