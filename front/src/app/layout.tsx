import {FunctionComponent, PropsWithChildren, useEffect} from 'react';
import {Metadata, Viewport} from 'next';
import {SimplePaletteColorOptions} from '@mui/material';
import {AppStoreProvider} from '@/store';
import defaultTheme, {ThemeProvider} from '@/theme';
import CurrentLayout from '@/layout';
import './globals.css';
import Script from "next/script";
import Head from 'next/head';
import KakaoScript from "@/app/KakaoScript";


const THEME_COLOR = (defaultTheme.palette?.primary as SimplePaletteColorOptions)?.main || '#FFFFFF';

export const viewport: Viewport = {
    themeColor: THEME_COLOR,
};

export const metadata: Metadata = {
    title: '_TITLE_',
    description: '_DESCRIPTION_',
    manifest: '/site.webmanifest',
    // TODO: Add Open Graph metadata
};

declare global {
    interface Window {
        Kakao: any;
    }
}


const RootLayout: FunctionComponent<PropsWithChildren> = ({children}) => {


    return (
        <html lang="en">

        <body>
        <KakaoScript/>
        <AppStoreProvider>
            <ThemeProvider>
                <CurrentLayout>{children}</CurrentLayout>
            </ThemeProvider>
        </AppStoreProvider>
        </body>
        </html>
    );
};

export default RootLayout;
