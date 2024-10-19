"use client"

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
import { useToast } from "@/hooks/use-toast"
import { useEffect, useRef, useState } from "react"


interface ChildProps {
    empId: number;
}

const PrintPaySheet: React.FC<ChildProps> = ({ empId, }) => {

    console.log("empId : " + empId)

    const [shouldClose, setShouldClose] = useState(false);

    const closeButtonRef = useRef<HTMLButtonElement>(null);

    {/* https://ui.shadcn.com/docs/components/toast */ }
    const { toast } = useToast()

    const [id, setId] = useState(0);

    // data fetching handle by this function
    const handlePrintRecipt = async (employeeId: number) => {


        const res = await fetch(
            "http://localhost:3000/api/print-recipt",
            {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({ employeeId }),
            }
        );

        if (res.ok) {
            toast({
                description: "Printing Successfully",
            })
            console.log("Success!!!")
            setShouldClose(!shouldClose)
        } else {
            toast({
                variant: "destructive",
                title: "Oh! Something went wrong!",
                description: "There was a problem with your request. Please Check the internet Connection",
            })
        }
    };


    useEffect(() => {

        setId(empId)

    }, []);

    useEffect(() => {
        if (shouldClose && closeButtonRef.current) {
            closeButtonRef.current.click();
        }
    }, [shouldClose]);

    return (
        <>
            <div className="flex justify-center content-center">
                <Dialog>
                    <DialogTrigger asChild>
                        {/* <Button variant="outline" className="bg-blue-500 w-full">Print</Button> */}
                        <button
                            className="bg-blue-500 my-1 w-24 rounded-lg hover:bg-blue-600"
                        >
                            Print
                        </button>
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
                                <Button
                                    type="submit"
                                    size="lg"
                                    className="bg-gray-600"
                                    onClick={() => handlePrintRecipt(id)}
                                >
                                    <span className="">Print</span>
                                </Button>
                                {/* close the window */}
                                <DialogClose asChild>
                                    <Button
                                        type="button"
                                        variant="secondary"
                                        ref={closeButtonRef}
                                    >
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

