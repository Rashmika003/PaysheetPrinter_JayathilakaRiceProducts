
import { Button } from "@/components/ui/button"
import {
    Dialog,
    DialogClose,
    DialogContent,
    DialogDescription,
    DialogHeader,
    DialogTitle,
    DialogTrigger,
} from "@/components/ui/dialog"


// todo :
// take employee ID and send that request to backend to print


const PrintPaySheet = () => {
    return (
        <>
            <div className="flex justify-center content-center">
                <Dialog>
                    <DialogTrigger asChild>
                        {/* <Button variant="outline" className="bg-blue-500 w-full">Print</Button> */}
                        <button className="bg-blue-500 my-1 w-24 rounded-lg hover:bg-blue-600">Print</button>
                    </DialogTrigger>
                    <DialogContent className="bg-gray-100">
                        <DialogHeader>
                            <DialogTitle className="text-black">Print Pay Sheet</DialogTitle>
                            <DialogDescription>
                                Please make sure,  <br />
                                1. printer is connected and powerd ON <br />
                                2. printer has enough paper <br />
                                3. printer warning lights are turned OFF
                            </DialogDescription>
                        </DialogHeader>
                        <div className="flex items-center space-x-2">
                            <div className="grid flex-1 gap-2">
                                <Button type="submit" size="lg" className="bg-gray-600">
                                    <span className="">Print</span>
                                </Button>
                                {/* close the window */}
                                <DialogClose asChild>
                                    <Button type="button" variant="secondary">
                                        Close
                                    </Button>
                                </DialogClose>
                            </div>
                        </div>
                    </DialogContent>
                </Dialog>


            </div>
        </>
    )
}
export default PrintPaySheet

