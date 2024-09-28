
export async function POST(request: Request) {

    const formData = await request.formData()

    console.log("FORM RECIEVED TO THE NEXT JS ENDPOINT : add-new-employee")

    try {
        const userData = {
            name: formData.get('name'),
            position: formData.get('position'),
            monthlySalary: formData.get('monthly_Salary'),
            ETF: formData.get('ETF'),
        };

        console.log(JSON.stringify(userData));

        const response = await fetch(`http://localhost:8085/api/v1/add`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(userData),
        });

        // Handle the response as needed

        if (response.ok) {
            console.log("Success!!!")

            // Get the response body as text
            const responseBodyText = await response.text();
            
            // TODO : handle errors thrown by server side
            console.log(response.status)
            console.log(responseBodyText)
            // return the response 
            const resData = { success: true, message: responseBodyText }

            return new Response(JSON.stringify(resData), {
                status: response.status
            });

        }
        else {
            console.log(response.status)

            // Get the response body as text
            const responseBodyText = await response.text();

            // TODO : handle errors thrown by server side
            console.log("Error in server API")
            console.log(response.status)
            console.log(responseBodyText)
            // return the response 
            const resData = { success: false, message: responseBodyText }

            return new Response(JSON.stringify(resData), {
                status: response.status
            });

        }
    }
    catch (error) {
        console.log("**************** Error *************")
        // console.log(error)
        return new Response(JSON.stringify({
            error: {
                message: "Un Expected Error"
            }
        }), { status: 500 });
    }
}








