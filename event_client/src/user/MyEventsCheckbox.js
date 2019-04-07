import React, { Component } from 'react'
import { Form, Checkbox } from 'semantic-ui-react'

export default class MyEventsCheckbox extends Component {
    constructor(props) {
        super(props);
        this.state = {
            value: "false",
        };
    }
    handleChange = (e, { value }) => {
        this.setState({ value },() => {this.props.updateToUserFavourite(this.state.value)})
    }

    render() {
        return (
            <Form>
                <Form.Field>
                    <Checkbox
                        radio
                        label='tik mano renginiai'
                        name='checkboxRadioGroup'
                        value='true'
                        checked={this.state.value === "true"}
                        onChange={this.handleChange}
                    />
                </Form.Field>
                <Form.Field>
                    <Checkbox
                        radio
                        label='visus renginiai'
                        name='checkboxRadioGroup'
                        value='false'
                        checked={this.state.value === "false"}
                        onChange={this.handleChange}
                    />
                </Form.Field>
            </Form>
        )
    }
}