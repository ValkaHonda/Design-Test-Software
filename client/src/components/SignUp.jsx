import React from 'react';

import { 
    Button,
    Checkbox,
    Form,
    Header
} from 'semantic-ui-react';

export default class SignUp extends React.Component {
    state = {
        email: '',
        pass: ''
    };

    onEmailChange = e => {
        this.setState({ email: e.target.value });
    };

    onPassChange = e => {
        this.setState({ pass: e.target.value });
    };

    sendRequest = () => {
        console.log(this.state);
    };

    render () {
        return <div className='auth-page'>
            <Header as='h2' icon textAlign='center'>
                <Header.Content>Sign Up</Header.Content>
            </Header>

            <Form>
                <Form.Field>
                    <Form.Input
                        label='Email'
                        placeholder='Email'
                        onChange={this.onEmailChange}
                    />
                </Form.Field>
                <Form.Field>
                    <Form.Input
                        label='Password'
                        placeholder='Password'
                        onChange={this.onPassChange}
                        type='password'
                    />
                </Form.Field>

                <Form.Field>
                    <Checkbox label='I agree to the Terms and Conditions' />
                </Form.Field>
                <Button type='submit' onClick={this.sendRequest}>Sign Up</Button>
            </Form>
        </div>;
    }
}