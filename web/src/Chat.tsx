import {Avatar, Box, Grid, List, ListItem, Typography} from "@mui/material";
import {AccountCircleOutlined} from "@mui/icons-material";
import React from "react";

export type ChatMessage = {
    sender: string
    message: string
}

export const ChatAvater = (props: {
    icon: boolean
    right: boolean
}) => {
    if (!props.icon) {
        return <Box sx={{width: '40px', height: '40px'}}/>
    } else {
        return props.right ? <Avatar src={"https://openai.com/content/images/2022/05/openai-avatar.png"}/>
            : <Avatar><AccountCircleOutlined/></Avatar>
    }
}

export const ChatMessageComponent = (props: {
    index: number
    message: string
    icon: boolean
    right: boolean
}) => {
    const backgroundColor = props.right ? '#7FFFD4' : '#FDA172'
    return <ListItem sx={{flexDirection: (props.right ? 'row-reverse' : 'row')}}>
        <ChatAvater {...props}/>
        <Box sx={{m: 1}}/>
        <Box border={'1px'} borderRadius={'16px'} padding={2} sx={{backgroundColor: backgroundColor}}>
            <Typography color={'black'}>{props.message}</Typography>
        </Box>
    </ListItem>
}

export const ChatWindow = (props: { chat: ChatMessage[] }) => {
    return <Grid id="chat-window" alignItems={"center"}
                 sx={{
                     height: 'calc(100% - 18px)',
                     width: '100%',
                     overflow: 'auto',
                     overscrollBehavior: 'contain',
                     flexFlow: 'wrap-reverse'
                 }}>
        <List id="chat-window-messages">
            {
                props.chat.map((message, index) => {
                    const right = message.sender !== ''
                    const icon = props.chat.at(index + 1)?.sender !== message.sender
                    return <ChatMessageComponent  key={index} index={index} message={message.message} icon={icon} right={right}/>
                })
            }
            <ListItem/>
        </List>
    </Grid>
}
