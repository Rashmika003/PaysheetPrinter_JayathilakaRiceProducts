
import "./globals.css";

import { Inter } from "next/font/google";
import "./globals.css";
import { cn } from "@/lib/utils";
import { Toaster } from "@/components/ui/toaster";
const inter = Inter({ subsets: ["latin"] });


export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en" className="h-full">
      {/* this cn() help us to add multiple class names and render then conditionally */}
      <body className={cn("relative h-full font-sans antialiased bg-white", inter.className)}>
        <main className="relative flex flex-col min-h-screen">
        {/* <Navbar /> */}
          <div className="flex-grow flex-1">{children}</div>
        
        {/* https://ui.shadcn.com/docs/components/toast */}
          <Toaster />

        </main>
        
      </body>
    </html>
  );
}
