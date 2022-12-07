import {
    Button,
    Dialog,
    DialogActions,
    DialogContent,
    Input,
    MenuItem,
    Stack,
    TextField,
    Typography
} from "@mui/material";
import React, {useState} from "react";

export const SettingsDialog = (props: {
    open: boolean,
    initialModel: string,
    initialMaxTokens: number | undefined,
    models: string[],
    onSave: (model: string, maxTokens: number | undefined) => void
}) => {
    const [model, setModel] = useState(props.initialModel)
    const [maxTokens, setMaxTokens] = useState<number | undefined>(props.initialMaxTokens);

    return <Dialog open={props.open}>
        <DialogContent>
            <Stack width={'400px'} spacing={4}>
                <TextField
                    select
                    label="Select Model"
                    value={model}
                    onChange={(e) => setModel(e.target.value)}
                    fullWidth
                >
                    {props.models.sort().map((model) => (
                        <MenuItem key={model} value={model}>
                            {model}
                        </MenuItem>
                    ))}
                </TextField>
                <Stack sx={{paddingX: 1}} direction={'row'} alignItems={'center'} spacing={2}>
                    <Typography>
                        Max Tokens
                    </Typography>
                    <Input
                        sx={{width: '80px'}}
                        value={maxTokens}
                        placeholder={'16'}
                        onChange={(e) => setMaxTokens(e.target.value === '' ? undefined : Number(e.target.value))}
                        inputProps={{
                            step: 8,
                            min: 0,
                            max: 4096,
                            type: 'number',
                        }}
                    />
                </Stack>
            </Stack>
        </DialogContent>
        <DialogActions>
            <Button onClick={() => props.onSave(model, maxTokens)}>Apply</Button>
        </DialogActions>
    </Dialog>
}