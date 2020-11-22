$(document).ready(function(){
    $('#employeeForm').bootstrapValidator({
    feedbackIcons: {
        valid: 'glyphicon glyphicon-ok',
        invalid: 'glyphicon glyphicon-remove',
        validating: 'glyphicon glyphicon-refresh'
    },
    fields: {
        firstName: {
            validators: {
                notEmpty: {
                    message: 'Please enter first name'
                }
            }
        },
        middleName: {
            validators: {
                notEmpty: {
                    message: 'Please enter middle name'
                }
            }
        },
        lastName: {
            validators: {
                notEmpty: {
                    message: 'Please enter last name'
                }
            }
        },
        sex: {
            validators: {
                notEmpty: {
                    message: 'Please choose a sex'
                }
            }
        },
        birthDate: {
            validators: {
                notEmpty: {
                    message: 'Please enter date of birth'
                }
            }
        },
        address: {
            validators: {
                notEmpty: {
                    message: 'Please enter address'
                }
            }
        },
        city: {
            validators: {
                notEmpty: {
                    message: 'Please enter city'
                }
            }
        },
        state: {
            validators: {
                notEmpty: {
                    message: 'Please enter state'
                }
            }
        },
        workLocation: {
            validators: {
                notEmpty: {
                    message: 'Please enter Work Location'
                }
            }
        },
        designation: {
            validators: {
                notEmpty: {
                    message: 'Please enter designation'
                }
            }
        },
        department: {
            validators: {
                notEmpty: {
                    message: 'Please enter department'
                }
            }
        },
        hireDate: {
            validators: {
                notEmpty: {
                    message: 'Please enter date of hire'
                }
            }
        },

        hra: {
            validators: {
                numeric: {
                    message: 'HRA must be a number'
                },
                notEmpty: {
                    message: 'Please enter HRA'
                }
            }
        },
        lta: {
            validators: {
                numeric: {
                    message: 'LTA must be a number'
                },
                notEmpty: {
                    message: 'Please enter LTA'
                }
            }
        },
        pfNo: {
            validators: {
                numeric: {
                    message: 'PFNo must be a number'
                },
                notEmpty: {
                    message: 'Please enter PFNo'
                }
            }
        },
        bankAcctNo: {
            validators: {
                numeric: {
                    message: 'Bank Account No must be a number'
                },
                notEmpty: {
                    message: 'Please enter bank account no'
                }
            }
        },
        basicSalary: {
            validators: {
                numeric: {
                    message: 'Basic salary must be a number'
                },
                notEmpty: {
                    message: 'Please enter basic salary'
                }
            }
        },
        conveyanceAllowance: {
            validators: {
                numeric: {
                    message: 'Conveyance Allowance must be a number'
                },
                notEmpty: {
                    message: 'Please enter conveyance allowance'
                }
            }
        },
        medicalAllowance: {
            validators: {
                numeric: {
                    message: 'Medical allowance must be a number'
                },
                notEmpty: {
                    message: 'Please enter medical allowance'
                }
            }
        },
        specialAllowance: {
            validators: {
                numeric: {
                    message: 'Special allowance must be a number'
                },
                notEmpty: {
                    message: 'Please enter special allowance'
                }
            }
        },
        incomeTax: {
            validators: {
                numeric: {
                    message: 'Income tax must be a number'
                },
                notEmpty: {
                    message: 'Please enter income tax'
                }
            }
        },

    }
    });
});