
export async function POST(request: Request) {

    console.log("delete-employees Nextjs API has Called");

    const reqParams = await request.json()
    
    
    const empId = reqParams.empId
    console.log(empId)

    try {

        const response = await fetch(`http://localhost:8085/api/v1/delete/${empId}`,{
            method: 'DELETE',
        }
        );

        // console.log("topselling request Sent");

        // Handle the response as needed
        if (response.ok) {

            const responseBodyText = await response.text();

            console.log(responseBodyText)

            // return the response 
            const resData = { success: false, message: responseBodyText }

            return new Response(JSON.stringify(resData), {
                status: response.status
            });

        } else {

            console.error("fetch-all-employees NextJs API failed : code " + response.status);

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

    } catch (error) {
        // console.error(error);

        const responseBodyText = "Next JS server Side Error!!!"

        console.error(responseBodyText)

        // return the response 
        const resData = { success: false, message: responseBodyText }

        return new Response(JSON.stringify(resData), {
            status: 500
        });
    }
}

