
export async function POST(request: Request) {

    const formData = await request.formData()

    // get the email as string
    const id = formData.get('id')?.toString() || '';
    console.log(id + "********id*********")

    console.log("FORM RECIEVED TO THE NEXT JS ENDPOINT")

    try {
        const userData = {
            name: formData.get('name'),
            position: formData.get('position'),
            monthly_Salary: formData.get('monthly_Salary'),
            bonus: formData.get('bonus'),
            advance_payments: formData.get('advance_payments'),
            loan_to_pay: formData.get('loan_to_pay'),
            loan_payment_for_month: formData.get('loan_payment_for_month'),
            worked_days_count: formData.get('worked_days_count'),
            should_work_dates_total: formData.get('should_work_dates_total'),
            etf: formData.get('etf'),
        };

        console.log(JSON.stringify(userData));

        const response = await fetch(`http://localhost:8085/api/v1/update/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(userData),
        });

        // Handle the response as needed

        if (response.ok) {
            const data = await response.text();

            console.log(data)

            return new Response(JSON.stringify(data));

        }
        else {
            console.error("update-employee NextJs API failed : code " + response.status);

            console.error(response.status)

            // Get the response body as text
            const responseBodyText = await response.text();

            console.error(responseBodyText)

            // return the response 
            const resData = { success: false, message: responseBodyText }

            return new Response(JSON.stringify(resData), {
                status: response.status
            });
        }
    }
    catch (error) {
        const responseBodyText = "Next JS server Side Error!!!"

        console.error(responseBodyText)

        // return the response 
        const resData = { success: false, message: responseBodyText }

        return new Response(JSON.stringify(resData), {
            status: 500
        });
    }
}


