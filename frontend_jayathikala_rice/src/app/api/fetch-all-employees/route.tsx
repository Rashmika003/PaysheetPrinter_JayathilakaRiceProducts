export const dynamic = "force-dynamic";


export async function GET() {

    console.log("fetch-all-employees Nextjs API has Called");

    try {

        const response = await fetch('http://localhost:8085/api/v1/getAll',
            { cache: 'no-store' }
        );

        // console.log("topselling request Sent");

        // Handle the response as needed
        if (response.ok) {

            // console.log('fetching topSelling Success');
            // console.log('fetching topSelling Success');

            const data = await response.json();

            console.log(data)

            return new Response(JSON.stringify(data));

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





