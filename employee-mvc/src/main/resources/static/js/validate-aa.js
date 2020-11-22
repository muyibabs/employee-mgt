$(document).ready(function(){
    $('#validateForm').bootstrapValidator({
    feedbackIcons: {
        valid: 'glyphicon glyphicon-ok',
        invalid: 'glyphicon glyphicon-remove',
        validating: 'glyphicon glyphicon-refresh'
    },
    fields: {
        full_name: {
            validators: {
                stringLength: {
                    min: 5,
                    message: 'Please Enter your Full name with minimum 5 letters length'
                },
                notEmpty: {
                    message: 'Please Enter your Full name'
                }
            }
        },
        phone: {
            validators: {
                numeric: {
                    message: 'The phone no must be a number'
                },
                notEmpty: {
                    message: 'Please Enter your phone number'
                }
            }
        },
        address: {
            validators: {
                stringLength: {
                    min: 15,
                    max: 100,
                    message:'Please enter at least 15 characters and no more than 100'
                },
                notEmpty: {
                    message: 'Please Enter Address'
                }
            }
        },
        gender: {
            validators: {
                notEmpty: {
                    message: 'The gender option is required'
                }
            }
        },
        email: {
            validators: {
                notEmpty: {
                    message: 'Please Enter your email address'
                },
                emailAddress: {
                    message: 'Please Enter a valid email address'
                }
            }
        },
        password: {
            validators: {
                notEmpty: {
                    message: 'Enter your profile password'
                }
            }
        },
        confirmPassword: {
            validators: {
                notEmpty: {
                    message: 'Enter confirm your profile password'
                },
                identical: {
                    field: 'password',
                    message: 'Enter the password, what enter in password field'
                }
            }
         },
         'lang_known[]': {
            validators: {
                notEmpty: {
                    message: 'The Language Known is required'
                }
            }
        },
        role: {
            validators: {
                notEmpty: {
                    message: 'Choose your user role'
                }
            }
        },

        }
    });
});